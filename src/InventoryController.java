import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private ObservableList<Product> productList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("productId"));

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));

        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        tableView.setItems(productList);
    }

    @FXML
    private void handleAddProduct(ActionEvent event) {
        Product newProduct =
                new Product(1, "Sample Product", 9.99, 5);

        productList.add(newProduct);
    }

    @FXML
    private void handleRemoveProduct(ActionEvent event) {
        Product selected =
                tableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            productList.remove(selected);
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }
}