package part_add;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.Inventory;
import org.w3c.dom.ls.LSOutput;
import part.InHouse;
import part.Outsourced;
import part.Part;
import part.PartService;
import part_modify.PartModifyService;
import utilities.Validator;
import utilities.WindowUtility;

import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class PartAddController implements Initializable {
    public Button addPartSaveButton;
    public Button addPartCancelButton;
    public Label addPartCompanyMachineLabel;
    public TextField addPartIDTextField;
    public TextField addPartNameTextField;
    public TextField addPartInvTextField;
    public TextField addPartPriceTextField;
    public TextField addPartMaxTextField;
    public TextField addPartMinTextField;
    public TextField addPartCompanyMachineTextField;
    public RadioButton addPartOutsourceRadio;
    public RadioButton addPartInHouseRadio;

    private Integer validInv;
    private Double validPrice;
    private Integer validMin;
    private Integer validMax;
    private String validCompany;
    private Integer validMachine;

    public void onMouseClickedSaveButton(){
        System.out.println(Inventory.getAllParts());
        if(PartModifyService.modifyPartID.equals(-1))
            save();
        else {
            update();
        }
    }

    public void onMouseClickedCancelButton(){
        WindowUtility.closeWindow(addPartCancelButton);
    }

    public void onMouseClickedOutsourceRadio(){
        companyMachineDisplaySwap();
    }

    public void onMouseClickedInHouseRadio(){
        companyMachineDisplaySwap();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(PartModifyService.modifyPartID != -1){
         insertSelectedData();
        }
    }

    /**
     * Private Methods. Usually used for UI changes, No logic for data handled here
     */
    private void save(){
        if(isInHouseSelected()){
            if( PartService.add( generateInHousePart() ) )
                WindowUtility.closeWindow(addPartSaveButton);
        }else if( PartService.add( generateOusourcedPart() ) )
            WindowUtility.closeWindow(addPartSaveButton);

        System.out.println(Inventory.getAllParts());

        resetModifyPartID();
    }



    private void update(){
        Integer id = PartModifyService.modifyPartID;

        if(isInHouseSelected())
            PartService.update( generateInHousePart(id) );
        else
            PartService.update( generateOusourcedPart(id) );

        System.out.println(Inventory.getAllParts());
        resetModifyPartID();

        WindowUtility.closeWindow(addPartSaveButton);
    }

    private void insertSelectedData(){
        Part selectedPart = Inventory.lookupPart(PartModifyService.modifyPartID).isPresent() ? Inventory.lookupPart(PartModifyService.modifyPartID).get() : null;
        addPartIDTextField.setText(selectedPart.getId().toString());
        addPartNameTextField.setText(selectedPart.getName());
        addPartInvTextField.setText(selectedPart.getStock().toString());
        addPartPriceTextField.setText(selectedPart.getPrice().toString());
        addPartMaxTextField.setText(selectedPart.getMax().toString());
        addPartMinTextField.setText(selectedPart.getMin().toString());
        if(selectedPart instanceof InHouse)
            addPartCompanyMachineTextField.setText(((InHouse) selectedPart).getMachineId().toString());
        else if(selectedPart instanceof Outsourced)
            addPartCompanyMachineTextField.setText(((Outsourced) selectedPart).getCompanyName());
    }


    private Outsourced generateOusourcedPart(){
        try {
            if(!isValidInput())
                throw new Exception();

            return new Outsourced(generateID(),
                    Validator.defaultValue(this.addPartNameTextField.getText()),
                    validPrice,
                    validInv,
                    validMin,
                    validMax,
                    validCompany);
        }catch (Exception e){
            System.out.println("Issue Parsing Outsourced Part: " + e.getMessage() );
        }
        return null;
    }

    private Outsourced generateOusourcedPart(Integer id){
        try {
            if(!isValidInput())
                throw new Exception();

            return new Outsourced(id,
                    Validator.defaultValue(this.addPartNameTextField.getText()),
                    validPrice,
                    validInv,
                    validMin,
                    validMax,
                    validCompany);
        }catch (Exception e){
            System.out.println("Issue Parsing Outsourced Part: " + e.getMessage() );
        }
        return null;
    }

    private InHouse generateInHousePart(){
        try {
            setValidMachine();

            if(!isValidInput())
                throw new Exception();

            return new InHouse(generateID(),
                    Validator.defaultValue(this.addPartNameTextField.getText()),
                    validPrice,
                    validInv,
                    validMin,
                    validMax,
                    validMachine);
        }catch (Exception e){
            System.out.println("Issue Parsing InHouse Part: " + e.getMessage() );
        }
        return null;
    }

    private InHouse generateInHousePart(Integer id){
        try {
            setValidMachine();

            if(!isValidInput())
                throw new Exception();

            return new InHouse( id,
                    Validator.defaultValue(this.addPartNameTextField.getText()),
                    validPrice,
                    validInv,
                    validMin,
                    validMax,
                    validMachine);
        }catch (Exception e){
            System.out.println("Issue Parsing InHouse Part: " + e.getMessage() );
        }
        return null;
    }

    private void resetModifyPartID() {
        PartModifyService.modifyPartID = -1;
    }

    private Integer generateID(){
        return Validator.uniqueID();
    }
    private Boolean isInHouseSelected(){
        return addPartInHouseRadio.selectedProperty().get();
    }

    private void companyMachineDisplaySwap(){
        if(isInHouseSelected())
            setMachineID();
        else
            setCompanyName();
    }
    private void setCompanyName(){
        addPartCompanyMachineLabel.setText("Company Name");
        addPartCompanyMachineTextField.setPromptText("Comp Nm");
    }
    private void setMachineID(){
        addPartCompanyMachineLabel.setText("Mach ID");
        addPartCompanyMachineTextField.setPromptText("Machine ID");
    }

    /**
     * @param code:
     *            0: Inserted Successfuly
     *            1: Missing any Data
     *            2: inventory value invalid
     *            3: min/max fields are invalid
     */
    private void saveCallback(Integer code){

    }

    private Boolean isValidInput(){
        setValidMin();
        setValidPrice();
        setValidMax();
        setValidCompany();
        return  setValidInv();
    }

    private Boolean setValidInv() {
        this.validInv = Validator.parseInt(this.addPartInvTextField.getText());
        return Validator.validateMinMaxInput(this.validMin, this.validMax) && Validator.validateInv(this.validMin, this.validMax, this.validInv);
    }

    private void setValidPrice() {
        this.validPrice = Validator.defaultValue( Validator.parseDouble(this.addPartPriceTextField.getText() ) );
    }

    private void setValidMin() {
        this.validMin = Validator.defaultValue( Validator.parseInt(this.addPartMinTextField.getText() ) );
    }

    private void setValidMax() {
        this.validMax = Validator.defaultValue( Validator.parseInt(this.addPartMaxTextField.getText() ) );
    }

    private void setValidCompany() {
        this.validCompany = Validator.defaultValue(this.addPartCompanyMachineTextField.getText());
    }

    private void setValidMachine() {
        this.validMachine = Validator.defaultValue( Validator.parseInt(this.addPartCompanyMachineTextField.getText() ) );
    }

}
