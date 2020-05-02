package part;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utilities.WindowUtility;

public class PartController {

    public Button partSearchButton;
    public TextField partSearchTextField;
    public Button partAddButton;
    public Button partModifyButton;
    public Button partDeleteButton;

    public void onMouseClickedSearchButton(){

    }

    public void onMouseClickedAddButton(){
        WindowUtility.openWindowFXMLFile( new FXMLLoader(getClass().getResource("/part_add/part_add.fxml")) );
    }

    public void onMouseClickedModifyButton(){
        WindowUtility.openWindowFXMLFile( new FXMLLoader(getClass().getResource("/part_modify/part_modify.fxml")) );
    }

    public void onMouseClickedDeleteButton(){

    }
}
