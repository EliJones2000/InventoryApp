public class Admin extends Employee {

    public Admin(int userId, String username, String password, String position) {
        super(userId, username, password, position);
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    // Admin-only ability
    public void clearInventory(Inventory inventory) {
        inventory.getProducts().clear();
    }
}