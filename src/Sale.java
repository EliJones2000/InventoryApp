import java.time.LocalDateTime;

public class Sale {

    private int saleId;
    private int productId;
    private String productName;
    private int quantitySold;
    private double sellPrice;
    private double totalAmount;
    private String timestamp;

    public Sale(int saleId, int productId, String productName,
                int quantitySold, double sellPrice,
                double totalAmount, String timestamp) {

        this.saleId = saleId;
        this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.sellPrice = sellPrice;
        this.totalAmount = totalAmount;
        this.timestamp = timestamp;
    }

    public int getSaleId() { return saleId; }
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantitySold() { return quantitySold; }
    public double getSellPrice() { return sellPrice; }
    public double getTotalAmount() { return totalAmount; }
    public String getTimestamp() { return timestamp; }
}