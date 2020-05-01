package product_add;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utilities.WindowUtility;

public class ProductAddController {
    public Button addProductSaveButton;
    public Button addProductCancelButton;
    public Button addProductSearchButton;
    public Button addProductAddButton;
    public Button addProductDeleteButton;
    public TextField addProductSearchTextField;
    public TextField addProductIDTextField;
    public TextField addProductNameTextField;
    public TextField addProductInvTextField;
    public TextField addProductPriceTextField;
    public TextField addProductMaxTextField;
    public TextField addProductMinTextField;

    public void onMouseClickedSaveButton(){}
    public void onMouseClickedCancelButton(){
        WindowUtility.closeWindow(addProductCancelButton);
    }
    public void onMouseClickSearchButton(){}
    public void onMouseClickAddButton(){}
    public void onMouseClickDeleteButton(){}


}
