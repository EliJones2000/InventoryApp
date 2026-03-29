import java.time.LocalDateTime;

public class Sale {

    private static int nextSaleId = 1;

    private int saleId;
    private Product product;
    private int quantitySold;
    private double priceAtSale;
    private double totalRevenue;
    private LocalDateTime timestamp;

    public Sale(Product product, int quantitySold) {
        this.saleId = nextSaleId++;
        this.product = product;
        this.quantitySold = quantitySold;
        this.priceAtSale = product.getSellPrice();
        this.totalRevenue = priceAtSale * quantitySold;
        this.timestamp = LocalDateTime.now();
    }

    public int getSaleId() {
        return saleId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getProfit() {
        return (product.getSellPrice() - product.getCostPrice())
                * quantitySold;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Sale #" + saleId +
                " | " + product.getName() +
                " | Qty: " + quantitySold +
                " | Revenue: $" + totalRevenue +
                " | Time: " + timestamp;
    }
}