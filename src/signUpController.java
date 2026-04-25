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

import java.io.FileWriter;
import java.io.IOException;

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

    @FXML
    void signUpComplete(ActionEvent event) {

        String email = enterEmail.getText();
        String username = createUsername.getText();
        String password = createPassword.getText();
        String reEnter = confirmPassword.getText();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || reEnter.isEmpty()) {
            error.setText("Please fill all fields");
            return;
        }

        if (!password.equals(reEnter)) {
            error.setText("Passwords do not match");
            return;
        }

        try {
            // Append to users.txt
            FileWriter writer = new FileWriter("users.txt", true);
            writer.write(username + "," + password + "\n");
            writer.close();

            error.setText("Registration successful!");

            // Return to Login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Inventory Management System");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            error.setText("Error saving user.");
        }
    }

    @FXML
    public void ReturneeLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}