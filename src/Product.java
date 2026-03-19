public class Product {

    // Fields
    private int productId;
    private String name;
    private double price;
    private int quantity;

    // Constructor
    public Product(int productId, String name, double price, int quantity){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public int getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }
    public  boolean isLowStock() {
        return quantity < 5; // threshold can be changed
    }

    // Display product info
    @Override
    public String toString() {
        return "ID: " + productId +
                ", Name: " + name +
                ", Price: $" + price +
                ", Quantity: " + quantity;
    }
}
