
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    //checks username and password
    private void checkLogin() throws IOException {

        InventoryApp app = new InventoryApp();

        if (username.getText().equals(signUpController.strg) && password.getText().equals(signUpController.strg)) {

            wrongLogin.setText("Login Successful");

            app.changeScene("Inventory.fxml");

        } else if (username.getText().isEmpty()
                || password.getText().isEmpty()) {

            wrongLogin.setText("Please enter your Username and Password");

        } else {
            wrongLogin.setText("Wrong username and Password");
        }
    }


    //change to sign up
    @FXML
    public void createaccount(ActionEvent event) {

        try{
            FXMLLoader loginLoad = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent loginRoot = loginLoad.load();

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(loginRoot);

            stage.setScene(scene);
            stage.setTitle("Inventory Management System");
            stage.show();
        }catch (IOException ev){
            ev.printStackTrace();
        }


    }
}