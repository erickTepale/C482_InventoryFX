package utilities;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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

    public static void warningMessage(String output){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Warning");
        window.setMinWidth(250);
        window.setMinHeight(200);

        Label warningMsg = new Label();
        warningMsg.setText(output);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(obj -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(warningMsg, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
