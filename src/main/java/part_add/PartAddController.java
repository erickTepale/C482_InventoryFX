package part_add;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.Inventory;
import part.InHouse;
import part.Outsourced;
import part.Part;
import part.PartService;
import utilities.Validator;
import utilities.WindowUtility;

import java.util.InputMismatchException;

public class PartAddController {
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
        if(isInHouseSelected()){
            if( PartService.add( generateInHousePart() ) )
                WindowUtility.closeWindow(addPartSaveButton);
        }else
            PartService.add( generateOusourcedPart() );

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


    /**
     * Private Methods. Usually used for UI changes, No logic for data handled here
     */

    private Outsourced generateOusourcedPart(){
        try {
            return new Outsourced(generateID(),
                    this.addPartNameTextField.getText(),
                    Double.parseDouble(this.addPartPriceTextField.getText()),
                    Integer.parseInt(this.addPartInvTextField.getText()),
                    Integer.parseInt(this.addPartMinTextField.getText()),
                    Integer.parseInt(this.addPartMaxTextField.getText()),
                    this.addPartCompanyMachineTextField.getText());
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
    private Integer generateID(){
        return Inventory.getAllParts().size() + 1;
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

    public Boolean setValidInv() {
        this.validInv = Validator.parseInt(this.addPartInvTextField.getText());
        return Validator.validateMinMaxInput(this.validMin, this.validMax) && Validator.validateInv(this.validMin, this.validMax, this.validInv);
    }

    public void setValidPrice() {
        this.validPrice = Validator.defaultValue( Validator.parseDouble(this.addPartPriceTextField.getText() ) );
    }

    public void setValidMin() {
        this.validMin = Validator.defaultValue( Validator.parseInt(this.addPartMinTextField.getText() ) );
    }

    public void setValidMax() {
        this.validMax = Validator.defaultValue( Validator.parseInt(this.addPartMaxTextField.getText() ) );
    }

    public void setValidCompany() {
        this.validCompany = Validator.defaultValue(this.addPartCompanyMachineTextField.getText());
    }

    public void setValidMachine() {
        this.validMachine = Validator.defaultValue( Validator.parseInt(this.addPartCompanyMachineTextField.getText() ) );
    }
}
