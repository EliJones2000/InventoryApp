// ==============================
// IMPORTS
// ==============================
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

import java.io.*;

// ==============================
// CLASS
// ==============================
public class InventoryController {

    private static final String FILE_NAME = "inventory.txt";
    private static final String SALES_FILE = "sales.txt";
    private int nextSaleId = 1;

    @FXML private TableView<Product> tableView;
    @FXML private TableColumn<Product, Integer> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Double> costColumn;
    @FXML private TableColumn<Product, Double> sellColumn;
    @FXML private TableColumn<Product, Integer> quantityColumn;

    @FXML private TextField searchField;
    @FXML private Label revenueLabel;
    @FXML private Label profitLabel;
    @FXML private Label inventoryValueLabel;
    @FXML private Label totalProductsLabel;
    @FXML private Label lowStockLabel;

    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private FilteredList<Product> filteredData;
    private int nextId = 1;

    // =================================
// NAVIGATION METHODS
// =================================

    @FXML
    private void goToSales(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sales.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sales Management");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==============================
    // INITIALIZE
    // ==============================

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        sellColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadFromFile();

        filteredData = new FilteredList<>(productList, p -> true);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(product -> {

                if (newVal == null || newVal.isEmpty()) return true;

                String filter = newVal.toLowerCase();

                return product.getName().toLowerCase().contains(filter) ||
                        String.valueOf(product.getProductId()).contains(filter);
            });
        });

        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);

        updateFinancialLabels();
        checkLowStock();

        tableView.setRowFactory(tv -> new TableRow<Product>() {
            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);

                if (product == null || empty) {
                    setStyle("");
                } else if (product.getQuantity() <= 5) {
                    setStyle("-fx-background-color: #ffdddd;");
                } else {
                    setStyle("");
                }
            }
        });

    }

    // ==============================
    // FILE SAVE
    // ==============================

    private void saveToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Product p : productList) {
                writer.write(p.getProductId() + "," +
                        p.getName() + "," +
                        p.getCostPrice() + "," +
                        p.getSellPrice() + "," +
                        p.getQuantity());
                writer.newLine();
            }

        } catch (IOException e) {
            showError("Error saving file.");
        }
    }

    // ==============================
    // FILE LOAD
    // ==============================

    private void loadFromFile() {

        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            int highestId = 0;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double cost = Double.parseDouble(parts[2]);
                double sell = Double.parseDouble(parts[3]);
                int qty = Integer.parseInt(parts[4]);

                productList.add(new Product(id, name, cost, sell, qty));

                if (id > highestId) {
                    highestId = id;
                }
            }

            nextId = highestId + 1;

        } catch (IOException e) {
            showError("Error loading file.");
        }
    }

    // ==============================
    // ADD PRODUCT
    // ==============================

    @FXML
    private void handleAddProduct() {

        String name = showInput("Enter Product Name:");
        if (name == null) return;

        try {
            double cost = Double.parseDouble(showInput("Enter Cost Price:"));
            double sell = Double.parseDouble(showInput("Enter Sell Price:"));
            int qty = Integer.parseInt(showInput("Enter Quantity:"));

            Product newProduct = new Product(nextId++, name, cost, sell, qty);
            productList.add(newProduct);

            updateFinancialLabels();
            saveToFile();

        } catch (Exception e) {
            showError("Invalid input.");
        }
    }

    // ==============================
    // REMOVE PRODUCT
    // ==============================

    @FXML
    private void handleRemoveProduct() {

        Product selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a product to remove.");
            return;
        }

        productList.remove(selected);
        updateFinancialLabels();
        saveToFile();
    }

    // ==============================
    // RESTOCK
    // ==============================

    @FXML
    private void handleRestockProduct() {

        Product selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a product first.");
            return;
        }

        try {
            int amount = Integer.parseInt(showInput("Enter quantity to add:"));

            if (amount <= 0) {
                showError("Invalid quantity.");
                return;
            }

            selected.setQuantity(selected.getQuantity() + amount);

            tableView.refresh();
            updateFinancialLabels();
            checkLowStock();
            saveToFile();

        } catch (Exception e) {
            showError("Invalid input.");
        }
    }

    // ==============================
    // FILTER METHODS (FIXED)
    // ==============================

    @FXML
    private void handleLowStock() {
        filteredData.setPredicate(product ->
                product.getQuantity() <= 5
        );
        checkLowStock();
    }

    @FXML
    private void handleShowAll() {
        filteredData.setPredicate(p -> true);
        lowStockLabel.setVisible(false);
    }

    @FXML
    private void handleClearSearch() {
        searchField.clear();
    }

    // ==============================
    // LOW STOCK LABEL
    // ==============================

    private void checkLowStock() {

        StringBuilder lowItems = new StringBuilder();

        for (Product product : productList) {
            if (product.getQuantity() <= 5) {
                lowItems.append(product.getName())
                        .append(" (Qty: ")
                        .append(product.getQuantity())
                        .append(")  ");
            }
        }

        if (lowItems.length() > 0) {
            lowStockLabel.setText("⚠ Low Stock: " + lowItems);
            lowStockLabel.setVisible(true);
        } else {
            lowStockLabel.setVisible(false);
        }
    }


    @FXML
    private void handleSellProduct() {

        Product selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Select a product to sell.");
            return;
        }

        try {
            int amount = Integer.parseInt(showInput("Enter quantity to sell:"));

            if (amount <= 0 || amount > selected.getQuantity()) {
                showError("Invalid quantity.");
                return;
            }

            selected.setQuantity(selected.getQuantity() - amount);

            double total = selected.getSellPrice() * amount;

            saveSale(selected, amount, total);

            tableView.refresh();
            updateFinancialLabels();
            saveToFile();

        } catch (Exception e) {
            showError("Invalid input.");
        }
    }
    private void saveSale(Product product, int qty, double total) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(SALES_FILE, true))) {

            String timestamp = java.time.LocalDateTime.now().toString();

            writer.write(nextSaleId++ + "," +
                    product.getProductId() + "," +
                    product.getName() + "," +
                    qty + "," +
                    product.getSellPrice() + "," +
                    total + "," +
                    timestamp);

            writer.newLine();

        } catch (IOException e) {
            showError("Error saving sale.");
        }
    }
    @FXML
    private void handleViewSales() {

        try {
            javafx.fxml.FXMLLoader loader =
                    new javafx.fxml.FXMLLoader(
                            getClass().getResource("sales.fxml"));

            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage =
                    (javafx.stage.Stage) tableView.getScene().getWindow();

            stage.setScene(new javafx.scene.Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==============================
    // FINANCIALS
    // ==============================

    private void updateFinancialLabels() {

        double revenue = 0;
        double profit = 0;
        double inventoryValue = 0;

        for (Product p : productList) {
            revenue += p.getSellPrice() * p.getQuantity();
            profit += (p.getSellPrice() - p.getCostPrice()) * p.getQuantity();
            inventoryValue += p.getCostPrice() * p.getQuantity();
        }

        revenueLabel.setText("Revenue: $" + String.format("%.2f", revenue));
        profitLabel.setText("Profit: $" + String.format("%.2f", profit));
        inventoryValueLabel.setText("Inventory Value: $" + String.format("%.2f", inventoryValue));
        totalProductsLabel.setText("Total Products: " + productList.size());
    }

    private String showInput(String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(message);
        return dialog.showAndWait().orElse(null);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}