package product;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utilities.WindowUtility;

public class ProductController {
    public Button productSearchButton;
    public TextField productSearchTextField;
    public Button productAddButton;
    public Button productModifyButton;
    public Button productDeleteButton;

    public void onMouseClickedSearchButton(){

    }

    public void onMouseClickedAddButton(){
        WindowUtility.openWindowFXMLFile( new FXMLLoader(getClass().getResource("/product_add/product_add.fxml")) );
    }

    public void onMouseClickedModifyButton(){
        WindowUtility.openWindowFXMLFile( new FXMLLoader(getClass().getResource("/product_modify/product_modify.fxml")) );
    }

    public void onMouseClickedDeleteButton(){

    }
}
