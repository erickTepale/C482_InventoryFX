package utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WindowUtility {

    public static void closeWindow(Button inputButton){
        ((Stage)inputButton.getScene().getWindow()).close();
    }

    public static void openWindowFXMLFile(FXMLLoader fxmlLoader){
        try {
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
