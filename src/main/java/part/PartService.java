package part;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Inventory;

import java.util.Optional;

/**
 * Handles all testable logic of the corresponding controller
 */
public class PartService {

    public static Integer add(Part part){
        return null;
    }

    public static ObservableList<Part> deletePart(Part toDelete){
        Inventory.lookupPart(toDelete.getId()).ifPresent(Inventory::deletePart);
        return Inventory.getAllParts();
    }

    /*
    * 1. Searches int/string query possibilities
    * 2. Joins int results into string results
    * 3. returns result set.
    *
    * 4. Returns all parts if query == ""
    */
    public static ObservableList<Part> search(String query){
        if (query.trim().isEmpty()) return Inventory.getAllParts();

        ObservableList<Part> tempString = searchStringValue(query);
        ObservableList<Part> tempInteger = searchIntValue(query);
        tempString.addAll(tempInteger);
        return tempString;
    }


    private static ObservableList<Part> searchStringValue(String query){
        return Inventory.lookupPart(query);
    }

    //Parses query to string and returns a result set based on if it succeeded
    private static ObservableList<Part> searchIntValue(String query){
        ObservableList<Part> temp = FXCollections.observableArrayList();
        try{
            Integer id = Integer.parseInt(query);
            Inventory.lookupPart(id).ifPresent(temp::add);
            return temp;
        }catch (NumberFormatException e){
            System.out.println("Attempted to Search a part ID that was not a number. All good though. ");
            return temp;
        }
    }



}
