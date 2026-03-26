import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InventoryApp extends Application {

    private static Stage stg;

    @Override
    public void start(Stage stage) throws Exception {
        stg = stage;//for changing login.fxml to inventory fxml
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Login.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();

        //Login fxml


    }
//to method to allow change from login.fxml to inventory.fxml
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}