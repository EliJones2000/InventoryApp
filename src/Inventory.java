import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> products;
    private ArrayList<Sale> sales;

    public Inventory() {
        products = new ArrayList<>();
        sales = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public void clearAllProducts() {
        products.clear();
        sales.clear();
    }

    public Product searchById(int id) {
        for (Product p : products) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public boolean sellProduct(int productId, int qty) {

        Product product = searchById(productId);

        if (product == null) return false;

        if (product.getQuantity() < qty) return false;

        product.setQuantity(product.getQuantity() - qty);

        Sale sale = new Sale(product, qty);
        sales.add(sale);

        return true;
    }

    public double getTotalInventoryValue() {
        double total = 0;
        for (Product p : products) {
            total += p.getInventoryValue();
        }
        return total;
    }

    public double getTotalRevenue() {
        double total = 0;
        for (Sale s : sales) {
            total += s.getTotalRevenue();
        }
        return total;
    }

    public double getTotalProfit() {
        double total = 0;
        for (Sale s : sales) {
            total += s.getProfit();
        }
        return total;
    }
}