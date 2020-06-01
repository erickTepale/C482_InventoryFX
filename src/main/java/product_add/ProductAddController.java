package product_add;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.Inventory;
import part.Part;
import part.PartService;
import part_modify.PartModifyService;
import product.Product;
import product.ProductService;
import product_modify.ProductModifyService;
import utilities.Validator;
import utilities.WindowUtility;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductAddController implements Initializable {
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
    public TableView<Part> partTableAllParts;
    public TableView<Part> partTableRelatedParts;

    private Integer validInv;
    private Double validCost;
    private Integer validMax;
    private Integer validMin;

    public void onMouseClickedSaveButton(){
        System.out.println(Inventory.getAllProducts());
        if(ProductModifyService.modifyProductId.equals(-1)) {
            System.out.println("Creating New Product: modifyProductID = " + ProductModifyService.modifyProductId);
            save();
        }else {
            System.out.println("Updating Product: modifyProductID = " + ProductModifyService.modifyProductId);
            update();
        }
    }

    public void onMouseClickedCancelButton(){
        WindowUtility.closeWindow(addProductCancelButton);
    }

    public void onMouseClickSearchButton(){}

    public void onMouseClickAddButton(){}

    public void onMouseClickDeleteButton(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!ProductModifyService.modifyProductId.equals(-1)){
            insertSelectedData();
        }
    }

    private void save(){
        if( ProductService.add( generateProduct() ) )
            WindowUtility.closeWindow(addProductCancelButton);
    }

    private void update(){
        ProductService.update(generateProduct( ProductModifyService.modifyProductId ));
        resetModifyPartID();

        WindowUtility.closeWindow(addProductCancelButton);
    }

    private void insertSelectedData(){
        Product selectedProduct = Inventory.lookupProduct(ProductModifyService.modifyProductId).isPresent() ? Inventory.lookupProduct(ProductModifyService.modifyProductId).get() : null;
        System.out.println(selectedProduct);
        addProductIDTextField.setText(selectedProduct.getId().toString());
        addProductNameTextField.setText(selectedProduct.getName());
        addProductInvTextField.setText(selectedProduct.getStock().toString());
        addProductPriceTextField.setText(selectedProduct.getPrice().toString());
        addProductMaxTextField.setText(selectedProduct.getMax().toString());
        addProductMinTextField.setText(selectedProduct.getMin().toString());
    }

    private Product generateProduct(){
        try {
            if (!isValidInput()) {
                throw new Exception();
            }

            return new Product(Validator.uniqueIDProduct(),
                                addProductNameTextField.getText(),
                                validCost,
                                validInv,
                                validMin,
                                validMax);
        }catch (Exception e){
            System.out.println("Issue Parsing Product: " + e.getMessage() );
        }
        return null;
    }

    private Product generateProduct(Integer id){
        try {
            if (!isValidInput()) {
                throw new Exception();
            }

            return new Product(id,
                    addProductNameTextField.getText(),
                    validCost,
                    validInv,
                    validMin,
                    validMax);
        }catch (Exception e){
            System.out.println("Issue Parsing Product: " + e.getMessage() );
        }
        return null;
    }

    private Boolean isValidInput(){
        setValidMin();
        setValidPrice();
        setValidMax();
        return  setValidInv();
    }

    private Boolean setValidInv() {
        this.validInv = Validator.parseInt(this.addProductInvTextField.getText());
        return Validator.validateMinMaxInput(this.validMin, this.validMax) && Validator.validateInv(this.validMin, this.validMax, this.validInv);
    }

    private void setValidPrice() {
        this.validCost = Validator.defaultValue( Validator.parseDouble(this.addProductPriceTextField.getText() ) );
    }

    private void setValidMin() {
        this.validMin = Validator.defaultValue( Validator.parseInt(this.addProductMinTextField.getText() ) );
    }

    private void setValidMax() {
        this.validMax = Validator.defaultValue( Validator.parseInt(this.addProductMaxTextField.getText() ) );
    }

    private void resetModifyPartID() {
        ProductModifyService.modifyProductId = -1;
    }
}
