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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class signUpController {

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
        Random rand = new Random();
        int randomnum = rand.nextInt(1,500);

        int userid = 1000000;

        if(email.isEmpty() || username.isEmpty() || password.isEmpty() || reEnter.isEmpty() || !password.equals(reEnter)){
            error.setText("Please fill all fields and make sure the password match");
        }
        else {

            int newid = userid + randomnum;

            User newuser = new User (username, password, email, User.UserRole.STAFF, newid);

            strg = Collections.singletonList(newuser);

            storeUserData(newuser);

            error.setText("registration successful"+ email);

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
        String data = strg.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(data);
            writer.newLine(); // Adds a system-dependent newline character
        } catch (IOException e) {
            e.printStackTrace();
        }
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
