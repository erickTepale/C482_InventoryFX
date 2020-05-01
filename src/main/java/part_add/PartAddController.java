package part_add;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import utilities.WindowUtility;

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

    public void onMouseClickedSaveButton(){}

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
    private void companyMachineDisplaySwap(){
        if(addPartInHouseRadio.selectedProperty().get())
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

}
