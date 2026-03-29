import java.io.*;
import javafx.collections.ObservableList;

public class FileManager {

    public void saveInventory(Inventory inventory, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            for (Product p : inventory.getProducts()) {
                writer.println(
                        p.getProductId() + "," +
                                p.getName() + "," +
                                p.getSellPrice() + "," +
                                p.getQuantity()
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadInventory(Inventory inventory, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);

                inventory.addProduct(new Product(id, name, price, price, quantity));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}