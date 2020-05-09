package utilities;

import main.Inventory;
import part.Part;

import java.text.ParseException;
import java.util.Comparator;

/**
 * Designed to validate user input
 */
public class Validator {
    public static Boolean validateSearchQuery(String query){
        return null;
    }

    public static String defaultValue(String input){
        return input.equals("") ? "n/a" : input;
    }

    public static Integer uniqueID (){
            return Inventory.getAllParts().stream().max(Comparator.comparing(Part::getId)).get().getId() + 1;
    }

    public static Integer defaultValue(Integer input){
        return (input < 0) ? 0 : input;

    }

    public static Double defaultValue(Double input){
        return (input < 0.0) ? 0.00 : input;
    }

    public static Integer parseInt(String input){
        try{
            return Integer.parseInt(input);
        }catch (Exception e){
            WindowUtility.warningMessage("Unable to accept '" + input + "' as a whole number.");
            System.out.println("Unable to parse String -> Integer: " + input);
        }
        return null;
    }

    // TODO: Remove commas from input
    public static Double parseDouble(String input){
        try{
            return Double.parseDouble(input);
        }catch (Exception e){
            WindowUtility.warningMessage("Unable to accept '" + input + "' as a number.");
            System.out.println("Unable to parse String -> Double: " + input);
        }
        return null;
    }

    public static  Boolean validateMinMaxInput(Integer min, Integer max){
        if(min <= max)
            return true;
        else
            WindowUtility.warningMessage("Min must be equal to or less than Max.");
        return null;
    }

    public static Boolean validateInv(Integer min, Integer max, Integer inv){
        if( min <= inv && inv <= max)
            return true;
        else
            WindowUtility.warningMessage("Inventory must be between the min and the max");
        return false;
    }
}
