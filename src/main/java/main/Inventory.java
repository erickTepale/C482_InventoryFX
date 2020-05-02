package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import part.Part;
import product.Product;

import java.util.Optional;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public static Optional<Part> lookupPart(String name){
        return allParts.stream().filter(obj -> obj.getName().equals(name)).findFirst();
    }

    public static Optional<Product> lookupProduct(String name){
        return allProducts.stream().filter(obj -> obj.getName().equals(name)).findFirst();
    }


}
