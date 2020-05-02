package part;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Inventory;
import utilities.DisplayTable;
import utilities.WindowUtility;

import java.net.URL;
import java.util.ResourceBundle;

public class PartController implements Initializable {
    public Button partSearchButton;
    public TextField partSearchTextField;
    public Button partAddButton;
    public Button partModifyButton;
    public Button partDeleteButton;
    public TableView<Part> partTableView;
    public TableColumn<String, Integer> idColumn;
    public TableColumn<String, String> nameColumn;
    public TableColumn<String, Integer> invColumn;
    public TableColumn<String, Double> priceColumn;



    public void onMouseClickedSearchButton(){

    }

    public void onMouseClickedAddButton(){
        WindowUtility.openWindowFXMLFile( new FXMLLoader(getClass().getResource("/part_add/part_add.fxml")) );
    }

    public void onMouseClickedModifyButton(){
        WindowUtility.openWindowFXMLFile( new FXMLLoader(getClass().getResource("/part_modify/part_modify.fxml")) );
    }

    public void onMouseClickedDeleteButton(){

    }

    public static void loadListenerData(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TableColumn<Part, String> idCol = new TableColumn<>("ID");
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.invColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.partTableView.setItems(Inventory.getAllParts());
    }
}
