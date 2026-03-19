import java.util.Scanner;

public class InventoryApp {
    public static void main(String[] args) {
       Inventory inventory = new Inventory();

       // Preload 10 products
       inventory.addProduct(new Product(101, "Keyboard", 29.99, 10));
       inventory.addProduct(new Product(102, "Mouse", 19.99, 25));
       inventory.addProduct(new Product(103, "Monitor", 199.99, 5));
       inventory.addProduct(new Product(104, "Laptop", 899.99, 7));
       inventory.addProduct(new Product(105, "Headphones", 49.99, 15));
       inventory.addProduct(new Product(106, "Webcam", 59.99, 8));
       inventory.addProduct(new Product(107, "Printer", 149.99, 3));
       inventory.addProduct(new Product(108, "USb Drive", 12.99, 50));
       inventory.addProduct(new Product(109, "Desk Lamp", 24.99, 12));
       inventory.addProduct(new Product(110, "Office Chair", 129.99, 4));

       System.out.println("==== INVENTORY SYSTEM STARTED ====\n");

       // Display all products
       inventory.displayAllProducts();

       // Display total inventory value
        System.out.println("\nTotal Inventory Value: $" + inventory.calculateTotalValue());

        // Display low stock alerts
        System.out.println("\nChecking for low stock items...");
        inventory.displayLowStock();

        System.out.println("\nSystem Ready.");
    }
}