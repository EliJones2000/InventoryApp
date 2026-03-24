public class Employee extends User {

    private String position;

    public Employee(int userId, String username, String password, String position) {
        super(userId, username, password);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String getRole() {
        return "Employee";
    }
}
