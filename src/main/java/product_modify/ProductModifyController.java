package product_modify;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utilities.WindowUtility;

public class ProductModifyController {
    public Button modifyProductSaveButton;
    public Button modifyProductCancelButton;
    public Button modifyProductSearchButton;
    public Button modifyProductAddButton;
    public Button modifyProductDeleteButton;
    public TextField modifyProductSearchTextField;
    public TextField modifyProductIDTextField;
    public TextField modifyProductNameTextField;
    public TextField modifyProductInvTextField;
    public TextField modifyProductPriceTextField;
    public TextField modifyProductMaxTextField;
    public TextField modifyProductMinTextField;

    public void onMouseClickedSaveButton(){}
    public void onMouseClickedCancelButton(){
        WindowUtility.closeWindow(modifyProductCancelButton);
    }
    public void onMouseClickSearchButton(){}
    public void onMouseClickAddButton(){}
    public void onMouseClickDeleteButton(){}
}
