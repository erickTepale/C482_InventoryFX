package product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Inventory;
import part.Part;

public class ProductService {

    /*
     * 1. Searches int/string query possibilities
     * 2. Joins int results into string results
     * 3. returns result set.
     *
     * 4. Returns all parts if query == ""
     */
    public static ObservableList<Product> search(String query){
        if (query.trim().isEmpty()) return Inventory.getAllProducts();

        ObservableList<Product> tempString = searchStringValue(query);
        ObservableList<Product> tempInteger = searchIntValue(query);
        tempString.addAll(tempInteger);
        return tempString;
    }


    private static ObservableList<Product> searchStringValue(String query){
        return Inventory.lookupProduct(query);
    }

    //Parses query to string and returns a result set based on if it succeeded
    private static ObservableList<Product> searchIntValue(String query){
        ObservableList<Product> temp = FXCollections.observableArrayList();
        try{
            Integer id = Integer.parseInt(query);
            Inventory.lookupProduct(id).ifPresent(temp::add);
            return temp;
        }catch (NumberFormatException e){
            System.out.println("Attempted to Search a product ID that was not a number. All good though. ");
            return temp;
        }
    }

    public static ObservableList<Product> deleteProduct(Product toDelete){
        Inventory.lookupProduct(toDelete.getId()).ifPresent(Inventory::deleteProduct);
        return Inventory.getAllProducts();
    }
}
