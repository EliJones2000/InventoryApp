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

import java.io.*;
import java.util.Scanner;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label wrongLogin;

    @FXML
    private void userLogin(ActionEvent event) throws IOException {
        checkLogin(event);
    }

    private void checkLogin(ActionEvent event) throws IOException {

        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            wrongLogin.setText("Please enter your Username and Password");
            return;
        }

        File file = new File("users.txt");

        if (!file.exists()) {
            wrongLogin.setText("No users found. Please sign up.");
            return;
        }

        Scanner scanner = new Scanner(file);

        boolean found = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            if (parts.length == 2) {
                String fileUser = parts[0];
                String filePass = parts[1];

                if (username.getText().equals(fileUser)
                        && password.getText().equals(filePass)) {
                    found = true;
                    break;
                }
            }
        }

        scanner.close();

        if (found) {
            wrongLogin.setText("Login Successful");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inventory.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } else {
            wrongLogin.setText("Wrong username or password");
        }
    }

    @FXML
    public void createaccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}