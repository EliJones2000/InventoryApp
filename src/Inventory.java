import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> products;

    // Constructor
    public Inventory() {
        products = new ArrayList<>();
    }

    // Getter for products list  ← ADD THIS
    public ArrayList<Product> getProducts() {
        return products;
    }

    // Add product
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added successfully.");
    }

    // Remove product by ID
    public boolean removeProduct(int productId) {
        for (Product p : products) {
            if (p.getProductId() == productId) {
                products.remove(p);
                System.out.println("Product removed.");
                return true;
            }
        }
        System.out.println("Product not found.");
        return false;
    }

    // Search product by ID
    public Product searchById(int productId) {
        for (Product p : products) {
            if (p.getProductId() == productId) {
                return p;
            }
        }
        return null;
    }

    // Search product by name
    public Product searchByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // Display all products
    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (Product p : products) {
            System.out.println(p);
        }
    }

    // Calculate total inventory value
    public double calculateTotalValue() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice() * p.getQuantity();
        }
        return total;
    }

    // Display low stock products
    public void displayLowStock() {
        for (Product p : products) {
            if (p.isLowStock()) {
                System.out.println("LOW STOCK ALERT: " + p);
            }
        }
    }
}