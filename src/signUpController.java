import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class signUpController {
    @FXML
    private Button createAccount;

    @FXML
    private Button returnLogin;

    @FXML
    private Label error;

    @FXML
    private TextField enterEmail;

    @FXML
    private PasswordField createPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField createUsername;

    static List<Object> strg = new ArrayList<>();

    @FXML
    void signUpComplete(ActionEvent event) {

        String email = enterEmail.getText();
        String username = createUsername.getText();
        String password = createPassword.getText();
        String reEnter = confirmPassword.getText();
        int userid = 10000;


        if(email.isEmpty() || username.isEmpty() || password.isEmpty() || reEnter.isEmpty() || !password.equals(reEnter)){
            error.setText("Please fill all fields and make sure the password match");
            return;
        }
        else {

            userid = userid+1;

            User newuser = new User (userid,username, email, password, User.UserRole.STAFF);

            storeUserData(newuser);

            error.setText("registration succesful"+ email);

            //returns to login

            try{
                FXMLLoader loginLoad = new FXMLLoader(getClass().getResource("Login.fxml"));
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

    private void storeUserData(User newuser){
        strg.add(newuser);
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
