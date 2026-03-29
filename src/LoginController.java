
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin;

    @FXML
    private void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {

        InventoryApp app = new InventoryApp();

        if (username.getText().equals("Username")
                && password.getText().equals("123")) {

            wrongLogin.setText("Login Successful");

            app.changeScene("Inventory.fxml");

        } else if (username.getText().isEmpty()
                || password.getText().isEmpty()) {

            wrongLogin.setText("Please enter your Username and Password");

        } else {
            wrongLogin.setText("Wrong username and Password");
        }
    }
}