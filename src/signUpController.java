import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class signUpController {
    @FXML
    private Button createAccount;


    @FXML
    private Button returnLogin;

    @FXML
    private TextField enterEmail;

    @FXML
    private PasswordField createPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField createUsername;

    @FXML
    void signUpComplete(ActionEvent event) {

        String email = enterEmail.getText();
        String username = createUsername.getText();
        String password = createPassword.getText();
        String reEnter = confirmPassword.getText();

        if(email.isEmpty() || username.isEmpty() || password.isEmpty() || reEnter.isEmpty()){
            System.out.println("Please fill all fields");
            return;
        }

        storeUserData(email, username,password);

        System.out.println("registration succesfull"+ email);

    }

    private void storeUserData(String email, String username, String password){
        return;
    }

    @FXML
    public void ReturneeLogin(ActionEvent e) {
        try{
            FXMLLoader loginLoad = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent loginRoot = loginLoad.load();

            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();

            Scene scene = new Scene(loginRoot);

            stage.setScene(scene);
            stage.setTitle("Inventory Management System");
            stage.show();
        }catch (IOException ev){
            ev.printStackTrace();
        }
    }
}
