import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product searchByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public Product searchById(int id) {
        for (Product p : products) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }

    public void increaseQuantity(String name, int amount) {
        Product product = searchByName(name);
        if (product != null) {
            product.setQuantity(product.getQuantity() + amount);
        }
    }

    public void decreaseQuantity(int id, int amount) {
        Product product = searchById(id);
        if (product != null) {
            int newQty = product.getQuantity() - amount;

            if (newQty <= 0) {
                products.remove(product);
            } else {
                product.setQuantity(newQty);
            }
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void clearAllProducts() {
        products.clear();
    }
}