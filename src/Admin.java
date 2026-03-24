public class Admin extends User {

    public Admin(int userId,
                 String username,
                 String passwordHash,
                 String email) {

        super(userId,
                username,
                passwordHash,
                email,
                UserRole.ADMIN);
    }

    // Admin-only ability
    public void clearInventory(Inventory inventory) {
        inventory.clearAllProducts();
        System.out.println("Inventory cleared by admin.");
    }
}