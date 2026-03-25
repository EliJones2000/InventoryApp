import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button Login;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin;

    @FXML
    public void userLogin(ActionEvent event) throws IOException {
        CheckLogin();

    }

    public void CheckLogin() throws IOException{
        InventoryApp i = new InventoryApp();
        if (username.getText().toString().equals("Username")&& password.getText().toString().equals("123")){
            wrongLogin.setText("Login Successfull");

            i.changeScene("Inventory.fxml");
        }
        else if (username.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogin.setText("Please enter your Username and Password");

        }
        else {
            wrongLogin.setText("Wrong username and Password");
        }

    }

}
