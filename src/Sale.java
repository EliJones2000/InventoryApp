import java.time.LocalDateTime;

public class Sale {

    private Product product;
    private int quantitySold;
    private LocalDateTime timestamp;

    public Sale(Product product, int quantitySold) {
        this.product = product;
        this.quantitySold = quantitySold;
        this.timestamp = LocalDateTime.now();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantitySold;
    }
}
