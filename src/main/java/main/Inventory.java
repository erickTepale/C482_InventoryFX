package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import part.Part;
import product.Product;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Inventory {
    protected static ObservableList<Part> allParts = FXCollections.observableArrayList();
    protected static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public static Optional<Part> lookupPart(Integer partId){
        return allParts.stream().filter(obj -> obj.getId().equals(partId)).findFirst();
    }

    public static ObservableList<Part> lookupPart(String name){
        return FXCollections.observableList( allParts.stream()
                                                .filter(obj -> obj.getName().toLowerCase().equals(name.toLowerCase()))
                                                .collect(Collectors.toCollection(ArrayList<Part>::new)) );
    }

    public static Optional<Product> lookupProduct(Integer productId){
        return allProducts.stream().filter(obj -> obj.getId().equals(productId)) .findFirst();
    }

    public static ObservableList<Product> lookupProduct(String name){
        return FXCollections.observableList( allProducts.stream()
                                                .filter(obj -> obj.getName().toLowerCase().equals(name.toLowerCase()))
                                                .collect(Collectors.toCollection(ArrayList<Product>::new)));
    }

    public static void updatePart(Integer index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(Integer index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }

    public static boolean deletePart(Part part){
        return allParts.remove(part);
    }

    public static boolean deleteProduct(Product product){
        return allProducts.remove(product);
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
