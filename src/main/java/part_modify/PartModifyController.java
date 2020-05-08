package part_modify;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.Inventory;
import part.InHouse;
import part.Outsourced;
import part.Part;
import utilities.WindowUtility;

import java.net.URL;
import java.util.ResourceBundle;

public class PartModifyController implements Initializable {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insertSelectedData();



    }

    /**
     * Private Methods. Usually used for UI changes, No logic for data handled here
     */
    private void insertSelectedData(){
            Part selectedPart = Inventory.lookupPart(PartModifyService.modifyPartID).isPresent() ? Inventory.lookupPart(PartModifyService.modifyPartID).get() : null;
            modifyPartIDTextField.setText(selectedPart.getId().toString());
            modifyPartNameTextField.setText(selectedPart.getName());
            modifyPartInvTextField.setText(selectedPart.getStock().toString());
            modifyPartPriceTextField.setText(selectedPart.getPrice().toString());
            modifyPartMaxTextField.setText(selectedPart.getMax().toString());
            modifyPartMinTextField.setText(selectedPart.getMin().toString());
            if(selectedPart instanceof InHouse)
                modifyPartCompanyMachineTextField.setText(((InHouse) selectedPart).getMachineId().toString());
            else if(selectedPart instanceof Outsourced)
                modifyPartCompanyMachineTextField.setText(((Outsourced) selectedPart).getCompanyName());
    }

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
