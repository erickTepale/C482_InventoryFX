package part_modify;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import utilities.WindowUtility;

public class PartModifyController {
    public Button modifyPartSaveButton;
    public Button modifyPartCancelButton;
    public Label modifyPartCompanyMachineLabel;
    public TextField modifyPartIDTextField;
    public TextField modifyPartNameTextField;
    public TextField modifyPartInvTextField;
    public TextField modifyPartPriceTextField;
    public TextField modifyPartMaxTextField;
    public TextField modifyPartMinTextField;
    public TextField modifyPartCompanyMachineTextField;
    public RadioButton modifyPartOutsourceRadio;
    public RadioButton modifyPartInHouseRadio;

    public void onMouseClickedSaveButton(){}

    public void onMouseClickedCancelButton(){
        WindowUtility.closeWindow(modifyPartCancelButton);
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
        if(modifyPartInHouseRadio.selectedProperty().get())
            setMachineID();
        else
            setCompanyName();
    }
    private void setCompanyName(){
        modifyPartCompanyMachineLabel.setText("Company Name");
        modifyPartCompanyMachineTextField.setPromptText("Comp Nm");
    }
    private void setMachineID(){
        modifyPartCompanyMachineLabel.setText("Mach ID");
        modifyPartCompanyMachineTextField.setPromptText("Machine ID");
    }
}
