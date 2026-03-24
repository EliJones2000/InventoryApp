import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Product> products;

    // Constructor
    public Inventory() {
        products = new ArrayList<>();
    }

    // ==============================
    // Add Product
    // ==============================
    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Cannot add null product.");
            return;
        }

        products.add(product);
        System.out.println("Product added successfully.");
    }

    // ==============================
    // Remove Product By ID
    // ==============================
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

    // ==============================
    // Search By ID
    // ==============================
    public Product searchById(int productId) {
        for (Product p : products) {
            if (p.getProductId() == productId) {
                return p;
            }
        }
        return null;
    }

    // ==============================
    // Search By Name
    // ==============================
    public Product searchByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // ==============================
    // Get All Products (Read Only)
    // ==============================
    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // Return copy (safer)
    }

    // ==============================
    // Clear Entire Inventory (Admin Use)
    // ==============================
    public void clearAllProducts() {
        products.clear();
        System.out.println("Inventory cleared.");
    }

    // ==============================
    // Display All Products
    // ==============================
    public void displayAllProducts() {

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (Product p : products) {
            System.out.println(p);
        }
    }

    // ==============================
    // Calculate Total Inventory Value
    // ==============================
    public double calculateTotalValue() {
        double total = 0;

        for (Product p : products) {
            total += p.getPrice() * p.getQuantity();
        }

        return total;
    }

    // ==============================
    // Low Stock Products
    // ==============================
    public List<Product> getLowStockProducts() {

        List<Product> lowStock = new ArrayList<>();

        for (Product p : products) {
            if (p.isLowStock()) {
                lowStock.add(p);
            }
        }

        return lowStock;
    }
}