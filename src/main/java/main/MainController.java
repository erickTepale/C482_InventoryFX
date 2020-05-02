package main;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import utilities.WindowUtility;

public class MainController {
    public Button exitButton;

    public void onMouseClickedExitButton(){
        WindowUtility.closeWindow(exitButton);
    }

}
