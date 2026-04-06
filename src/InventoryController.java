import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.Label;


public class InventoryController {

    // ==============================
    // FXML ELEMENTS
    // ==============================

    @FXML private TableView<Product> tableView;
    @FXML private TableColumn<Product, Integer> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Double> costColumn;
    @FXML private TableColumn<Product, Double> sellColumn;
    @FXML private TableColumn<Product, Integer> quantityColumn;

    @FXML private TextField searchField;
    @FXML private Label revenueLabel;
    @FXML private Label profitLabel;
    @FXML
    private Label inventoryValueLabel;
    @FXML
    private Label totalProductsLabel;
    @FXML
    private Label lowStockLabel;

    private double calculateInventoryValue() {
        double total = 0;
        for (Product p : productList) {
            total += p.getQuantity() * p.getCostPrice();
        }
        return total;
    }

    // ==============================
    // DATA
    // ==============================

    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private FilteredList<Product> filteredData;
    private int nextId = 26; // since we preload 25 items

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

        loadDefaultProducts();

        filteredData = new FilteredList<>(productList, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(product -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Search by Name
                if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                // Search by ID
                if (String.valueOf(product.getProductId()).contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
        updateFinancialLabels();

        checkLowStock();
    }


    // ==============================
    // PRELOAD PRODUCTS
    // ==============================

    private void loadDefaultProducts() {
        productList.addAll(
                new Product(1, "Milk", 1.50, 2.99, 12),
                new Product(2, "Bread", 1.00, 2.49, 15),
                new Product(3, "Eggs", 2.00, 3.99, 8),
                new Product(4, "Apples", 0.50, 1.25, 25),
                new Product(5, "Bananas", 0.30, 0.99, 30),
                new Product(6, "Orange Juice", 2.50, 4.49, 10),
                new Product(7, "Chicken Breast", 4.00, 7.99, 18),
                new Product(8, "Ground Beef", 3.50, 6.99, 14),
                new Product(9, "Rice (5lb)", 3.00, 5.49, 20),
                new Product(10, "Pasta", 1.20, 2.79, 22),
                new Product(11, "Cereal", 2.75, 4.99, 16),
                new Product(12, "Yogurt", 0.75, 1.49, 35),
                new Product(13, "Cheese", 2.50, 4.25, 11),
                new Product(14, "Butter", 1.80, 3.49, 9),
                new Product(15, "Tomatoes", 0.60, 1.79, 28),
                new Product(16, "Lettuce", 0.90, 1.99, 13),
                new Product(17, "Potatoes (10lb)", 4.00, 6.99, 7),
                new Product(18, "Onions", 0.40, 1.29, 19),
                new Product(19, "Coffee", 5.00, 8.99, 6),
                new Product(20, "Tea Bags", 2.20, 3.99, 17),
                new Product(21, "Frozen Pizza", 3.50, 6.49, 12),
                new Product(22, "Ice Cream", 2.75, 5.99, 8),
                new Product(23, "Soda (12-pack)", 4.00, 7.49, 14),
                new Product(24, "Water Bottles (24-pack)", 3.50, 6.99, 20),
                new Product(25, "Snack Chips", 1.50, 3.49, 26)
        );
    }

    private int getTotalProducts() {
        return productList.size();
    }

    // ==============================
    // ADD PRODUCT
    // ==============================

    @FXML
    private void handleAddProduct() {

        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setHeaderText("Enter Product Name:");
        String name = nameDialog.showAndWait().orElse(null);
        if (name == null) return;

        try {
            double cost = Double.parseDouble(showInput("Enter Cost Price:"));
            double sell = Double.parseDouble(showInput("Enter Sell Price:"));
            int qty = Integer.parseInt(showInput("Enter Quantity:"));

            Product newProduct = new Product(nextId++, name, cost, sell, qty);
            productList.add(newProduct);

            updateFinancialLabels();

        } catch (Exception e) {
            showError("Invalid input.");
        }
    }

    // ==============================
    // SELL PRODUCT
    // ==============================

    @FXML
    private void handleSellProduct() {

        Product selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a product first.");
            return;
        }

        try {
            int qty = Integer.parseInt(showInput("Enter Quantity To Sell:"));

            if (qty <= 0 || qty > selected.getQuantity()) {
                showError("Invalid quantity.");
                return;
            }

            selected.setQuantity(selected.getQuantity() - qty);
            tableView.refresh();
            updateFinancialLabels();

        } catch (Exception e) {
            showError("Invalid quantity.");
        }
    }

    @FXML
    private void handleLowStock() {

        filteredData.setPredicate(product ->
                product.getQuantity() <= 5
        );

        checkLowStock();
    }

    @FXML
    private void handleShowAll() {
        filteredData.setPredicate(null);
        lowStockLabel.setVisible(false);
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

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Removal");
        confirmAlert.setHeaderText("Are you sure you want to remove this product?");
        confirmAlert.setContentText("Product: " + selected.getName());

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            productList.remove(selected);
            updateFinancialLabels();
        }
    }
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
            lowStockLabel.setText("⚠ Low Stock: " + lowItems.toString());
            lowStockLabel.setVisible(true);
        } else {
            lowStockLabel.setVisible(false);
        }
    }
    @FXML
    private void handleClearSearch() {
        searchField.clear();
    }

    // ==============================
    // FINANCIALS
    // ==============================

    private void updateFinancialLabels() {

        double revenue = 0;
        double profit = 0;

        for (Product p : productList) {
            revenue += p.getSellPrice() * p.getQuantity();
            profit += (p.getSellPrice() - p.getCostPrice()) * p.getQuantity();
        }

        revenueLabel.setText("Revenue: " + String.format("$%.2f", revenue));
        profitLabel.setText("Profit: " + String.format("$%.2f", profit));
        inventoryValueLabel.setText("Inventory Value: $" +
                String.format("%.2f", calculateInventoryValue()));
        totalProductsLabel.setText("Total Products: " + getTotalProducts());
    }

    // ==============================
    // HELPERS
    // ==============================

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