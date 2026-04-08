import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesController {

    private static final String SALES_FILE = "sales.txt";

    @FXML private TableView<Sale> salesTable;
    @FXML private TableColumn<Sale, Integer> saleIdColumn;
    @FXML private TableColumn<Sale, String> productColumn;
    @FXML private TableColumn<Sale, Integer> quantityColumn;
    @FXML private TableColumn<Sale, Double> totalColumn;
    @FXML private TableColumn<Sale, String> timestampColumn;

    private ObservableList<Sale> salesList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        saleIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("saleId"));
        productColumn.setCellValueFactory(
                new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantitySold"));
        totalColumn.setCellValueFactory(
                new PropertyValueFactory<>("totalAmount"));
        timestampColumn.setCellValueFactory(
                new PropertyValueFactory<>("timestamp"));

        loadSales();
        salesTable.setItems(salesList);
    }

    private void loadSales() {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(SALES_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                Sale sale = new Sale(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        parts[2],
                        Integer.parseInt(parts[3]),
                        Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5]),
                        parts[6]
                );

                salesList.add(sale);
            }

        } catch (IOException e) {
            System.out.println("No sales yet.");
        }
    }
}