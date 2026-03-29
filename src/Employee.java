public class Employee extends User {

    private String position;

    public Employee(int userId,
                    String username,
                    String passwordHash,
                    String email,
                    UserRole role) {

        super(userId, username, passwordHash, email, role);
    }

    public String getPosition() {
        return position;
    }

    // DO NOT override getRole() now
    // It already returns UserRole from User
}
