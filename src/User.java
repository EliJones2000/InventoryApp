public class User {
    protected int userId;
    protected String username;
    protected String passwordHash; // Use secure password handling
    protected String email;
    protected UserRole role; // Using an Enum for roles (e.g., ADMIN, MANAGER)

    // Enum for User Roles
    public enum UserRole {
        ADMIN,
        MANAGER,
        STAFF
    }

    // Constructor
    public User(String username, String passwordHash, String email, UserRole role, int userId) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return username+","+passwordHash+","+"email:"+email+",role:"+role+",UserId:"+userId;
    }
}
