package product_add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Inventory;
import part.Part;
import part.PartService;
import product.Product;
import product.ProductService;
import product_modify.ProductModifyService;
import utilities.Validator;
import utilities.WindowUtility;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
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
    public TableColumn<String, Integer> idAllColumn;
    public TableColumn<String, String> nameAllColumn;
    public TableColumn<String, Integer> invAllColumn;
    public TableColumn<String, Double> priceAllColumn;
    public TableColumn<String, Integer> idRelatedColumn;
    public TableColumn<String, String> nameRelatedColumn;
    public TableColumn<String, Integer> invRelatedColumn;
    public TableColumn<String, Double> priceRelatedColumn;


    private Integer validInv;
    private Double validCost;
    private Integer validMax;
    private Integer validMin;

    private ObservableList<Part> associationParts = FXCollections.observableArrayList();
    private ArrayList<Part> originalAssociationParts = new ArrayList<>();


    //todo: handle assoc parts list according to each option
    public void onMouseClickedSaveButton(){
        System.out.println(Inventory.getAllProducts());
        if(ProductModifyService.modifyProductId.equals(-1)) {
            save();
            System.out.println("Created New Product: modifyProductID = " + ProductModifyService.modifyProductId);
        }else {
            update();
            System.out.println("Updated Product: modifyProductID = " + ProductModifyService.modifyProductId);
        }
    }

    public void onMouseClickedCancelButton(){
        //reset association
        associationParts = null;
        associationParts = FXCollections.observableArrayList();
        associationParts.addAll(originalAssociationParts);
        initializeRelated(associationParts);
        originalAssociationParts = new ArrayList<>();
        partTableRelatedParts.refresh();

        if(!ProductModifyService.modifyProductId.equals(-1)){
            Inventory.addAssociation(Inventory.lookupProduct(ProductModifyService.modifyProductId).get(), associationParts);
        }


        WindowUtility.closeWindow(addProductCancelButton);

    }

    public void onMouseClickSearchButton(){
        initialize( PartService.search( this.addProductSearchTextField.getText() ) );
    }

    public void onMouseClickAddButton(){
        associationParts.add( Inventory.lookupPart(partTableAllParts.getSelectionModel().getSelectedItem().getId()).get() );
        initializeRelated(associationParts);
    }

    public void onMouseClickDeleteButton(){
        associationParts.remove(Inventory.lookupPart(partTableRelatedParts.getSelectionModel().getSelectedItem().getId()).get());
        initializeRelated(associationParts);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(!ProductModifyService.modifyProductId.equals(-1)){
            insertSelectedData();
        }

        setAllPartsTable();
        setRelatedPartsTable();
        System.out.println("testtted " + associationParts );
    }

    public void initialize(ObservableList<Part> list){
        this.partTableAllParts.setItems(list);
    }
    public void initializeRelated(ObservableList<Part> list){
        this.partTableRelatedParts.setItems(list);
    }


    private void save(){
        Product product = generateProduct();

        if(!product.getName().isEmpty()) {
            if (isProductPriceValid()) {
                if(hasValidAssociatedParts()) {
                    if (ProductService.add(product)) {
                        Inventory.addAssociation(product, associationParts);
                        WindowUtility.closeWindow(addProductCancelButton);
                    }
                }
            } else {
                WindowUtility.warningMessage("The cost of the product: " + validCost + " is less than the sum of its parts: " + sumOfParts());
            }
        }else{
            WindowUtility.warningMessage("Name must contain values");
        }

    }

    private boolean hasValidAssociatedParts() {
        if(associationParts.size() > 0){
            if( associationParts.size() == new HashSet<>(associationParts).size() ){
                return true;
            }else {
                WindowUtility.warningMessage("All Associated parts must be unique");
            }
        }else{
            WindowUtility.warningMessage("Must have at least One Associated Part for the product.");
        }
        return false;
    }

    private void update(){
        //Generate Product
        Product generated = generateProduct(ProductModifyService.modifyProductId);


        if(isProductPriceValid()) {

            ProductService.update(generated);
            Inventory.addAssociation(Inventory.lookupProduct(ProductModifyService.modifyProductId).get(), associationParts);
            resetModifyProductID();

            WindowUtility.closeWindow(addProductCancelButton);
        }else {
            WindowUtility.warningMessage("The cost of the product: " + validCost + " is less than the sum of its parts: " + sumOfParts());
        }

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
        System.out.println("Generating Product from modify selection. Part id: " + id);
        try {
            //Price related validation
            if (!isValidInput()) {
                System.out.println("Exception thrown Invalid Input");
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

    //price related validation
    private Boolean isValidInput(){
        setValidMin();
        setValidPrice();
        setValidMax();
        return  setValidInv();
    }

    //requirement Part 2
    private Boolean isProductPriceValid(){
        return validCost >= sumOfParts();
    }

    private Double sumOfParts(){
        return associationParts.stream().mapToDouble(Part::getPrice).sum();
    }

    //REQUIREMENT PART 1
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

    private void resetModifyProductID() {
        ProductModifyService.modifyProductId = -1;
    }

    private void setAllPartsTable(){
        this.idAllColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nameAllColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.invAllColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.priceAllColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.partTableAllParts.setItems(Inventory.getAllParts());
    }

    private void setRelatedPartsTable(){
        this.idRelatedColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nameRelatedColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.invRelatedColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.priceRelatedColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        if(ProductModifyService.modifyProductId != -1){
            this.partTableRelatedParts.setItems(Inventory.getAssociation( Inventory.lookupProduct( ProductModifyService.modifyProductId ).get() ) );
            associationParts = Inventory.getAssociation( Inventory.lookupProduct( ProductModifyService.modifyProductId ).get() );
            originalAssociationParts.addAll( associationParts);
        }
    }
}
