public class ReportGenerator {

    public double calculateTotalInventoryValue(Inventory inventory) {

        double total = 0;

        for (Product p : inventory.getAllProducts()) {
            total += p.getPrice() * p.getQuantity();
        }

        return total;
    }

    public void printLowStockReport(Inventory inventory) {

        System.out.println("Low Stock Products:");

        for (Product p : inventory.getAllProducts()) {
            if (p.getQuantity() < 5) {
                System.out.println(
                        p.getName() + " - Quantity: " + p.getQuantity()
                );
            }
        }
    }
}
