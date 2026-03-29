public class Product {

    private int productId;
    private String name;
    private double costPrice;
    private double sellPrice;
    private int quantity;

    public Product(int productId, String name,
                   double costPrice, double sellPrice,
                   int quantity) {
        this.productId = productId;
        this.name = name;
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isLowStock() {
        return quantity < 5;
    }

    public double getInventoryValue() {
        return sellPrice * quantity;
    }

    @Override
    public String toString() {
        return name + " | Qty: " + quantity +
                " | Sell: $" + sellPrice;
    }
}