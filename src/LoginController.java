import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.IOException;

public class LoginController {
    @FXML
    private Button Login;

    @FXML
    private Button signup;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin;

    //login
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
    //

    //Sign up
    @FXML
    public void userSignup(ActionEvent e) {
        try{
            FXMLLoader signupLoad = new FXMLLoader(getClass().getResource("signUp.fxml"));
            Parent signupRoot = signupLoad.load();

            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();

            Scene scene = new Scene(signupRoot);

            stage.setScene(scene);
            stage.setTitle("Inventory Management System");
            stage.show();
        }catch (IOException ev){
            ev.printStackTrace();
        }
    }

}
