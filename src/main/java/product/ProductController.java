package product;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Inventory;
import utilities.WindowUtility;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    public Button productSearchButton;
    public TextField productSearchTextField;
    public Button productAddButton;
    public Button productModifyButton;
    public Button productDeleteButton;
    public TableView<Product> productTableView;
    public TableColumn<String, Integer> idColumn;
    public TableColumn<String, String> nameColumn;
    public TableColumn<String, Integer> inventoryColumn;
    public TableColumn<String, Double> priceColumn;

    public void onMouseClickedSearchButton(){

    }

    public void onMouseClickedAddButton(){
        WindowUtility.openWindowFXMLFile( new FXMLLoader(getClass().getResource("/product_add/product_add.fxml")) );
    }

    public void onMouseClickedModifyButton(){
        WindowUtility.openWindowFXMLFile( new FXMLLoader(getClass().getResource("/product_modify/product_modify.fxml")) );
    }

    public void onMouseClickedDeleteButton(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.productTableView.setItems(Inventory.getAllProducts());
    }
}
