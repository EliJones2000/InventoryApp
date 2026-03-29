import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventoryController {

    // ==============================
    // FXML UI ELEMENTS
    // ==============================

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> costColumn;

    @FXML
    private TableColumn<Product, Double> sellColumn;

    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    @FXML
    private Label revenueLabel;

    @FXML
    private Label profitLabel;

    // ==============================
    // DATA
    // ==============================

    private Inventory inventory = new Inventory();
    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private int nextId = 1;

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

        tableView.setItems(productList);

        updateFinancialLabels();
    }

    // ==============================
    // ADD PRODUCT
    // ==============================

    @FXML
    private void handleAddProduct() {

        String name = showInput("Enter Product Name:");
        if (name == null) return;

        String costStr = showInput("Enter Cost Price:");
        if (costStr == null) return;

        String sellStr = showInput("Enter Sell Price:");
        if (sellStr == null) return;

        String qtyStr = showInput("Enter Quantity:");
        if (qtyStr == null) return;

        try {
            double cost = Double.parseDouble(costStr);
            double sell = Double.parseDouble(sellStr);
            int qty = Integer.parseInt(qtyStr);

            if (cost < 0 || sell < 0 || qty <= 0) {
                showError("Values must be positive.");
                return;
            }

            Product product = new Product(nextId++, name, cost, sell, qty);

            inventory.addProduct(product);
            productList.add(product);

            tableView.refresh();
            updateFinancialLabels();

        } catch (NumberFormatException e) {
            showError("Invalid number entered.");
        }
    }

    // ==============================
    // SELL PRODUCT
    // ==============================

    @FXML
    private void handleSellProduct() {

        Product selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Please select a product first.");
            return;
        }

        String qtyStr = showInput("Enter Quantity To Sell:");
        if (qtyStr == null) return;

        try {
            int qty = Integer.parseInt(qtyStr);

            if (qty <= 0) {
                showError("Quantity must be greater than 0.");
                return;
            }

            boolean success = inventory.sellProduct(selected.getProductId(), qty);

            if (!success) {
                showError("Not enough stock.");
                return;
            }

            tableView.refresh();
            updateFinancialLabels();

        } catch (NumberFormatException e) {
            showError("Invalid quantity.");
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

        inventory.getProducts().remove(selected);
        productList.remove(selected);

        tableView.refresh();
        updateFinancialLabels();
    }

    // ==============================
    // UPDATE FINANCIAL LABELS
    // ==============================

    private void updateFinancialLabels() {

        revenueLabel.setText("Revenue: $" + inventory.getTotalRevenue());
        profitLabel.setText("Profit: $" + inventory.getTotalProfit());
    }

    // ==============================
    // HELPER METHODS
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