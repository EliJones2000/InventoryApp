public class Employee extends User {

    private String position;

    public Employee(int userId,
                    String username,
                    String passwordHash,
                    String email,
                    String position) {

        // Automatically assign STAFF role for employees
        super(userId,
                username,
                passwordHash,
                email,
                UserRole.STAFF);

        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    // DO NOT override getRole() now
    // It already returns UserRole from User
}
