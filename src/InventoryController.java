import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryController {

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    private final ObservableList<Product> productList =
            FXCollections.observableArrayList();

    private Inventory inventory = new Inventory();

    private int nextId = 1;

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("productId"));

        nameColumn.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("name"));

        quantityColumn.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("quantity"));

        priceColumn.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("price"));

        tableView.setItems(productList);
    }

    // ==============================
    // ADD PRODUCT (MULTIPLE SUPPORT)
    // ==============================
    @FXML
    private void handleAddProduct() {

        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setHeaderText("Enter Product Name:");
        String name = nameDialog.showAndWait().orElse(null);

        if (name == null || name.isBlank()) return;

        TextInputDialog qtyDialog = new TextInputDialog("1");
        qtyDialog.setHeaderText("Enter Quantity:");
        String qtyStr = qtyDialog.showAndWait().orElse(null);

        if (qtyStr == null) return;

        int qty = Integer.parseInt(qtyStr);

        Product existing = inventory.searchByName(name);

        if (existing != null) {
            inventory.increaseQuantity(name, qty);
            tableView.refresh();
        } else {
            Product product = new Product(nextId++, name, 10.0, qty);
            inventory.addProduct(product);
            productList.add(product);
        }
    }

    // ==============================
    // REMOVE QUANTITY
    // ==============================
    @FXML
    private void handleRemoveProduct() {

        Product selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        TextInputDialog qtyDialog = new TextInputDialog("1");
        qtyDialog.setHeaderText("Enter Quantity to Remove:");
        String qtyStr = qtyDialog.showAndWait().orElse(null);

        if (qtyStr == null) return;

        int qty = Integer.parseInt(qtyStr);

        inventory.decreaseQuantity(selected.getProductId(), qty);

        if (selected.getQuantity() <= 0) {
            productList.remove(selected);
        }

        tableView.refresh();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}