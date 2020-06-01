package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import part.Part;
import product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

public class Inventory {
    protected static ObservableList<Part> allParts = FXCollections.observableArrayList();
    protected static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    protected static HashMap<Product, ObservableList<Part>> productPartMap = new HashMap<>();

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
        Part toRemove = allParts.get(index);
        allParts.remove(toRemove);
        allParts.add(selectedPart);
    }

    public static void updatePart(Part selectedPart){
        Part toRemove = lookupPart(selectedPart.getId()).get();
        allParts.remove(toRemove);
        allParts.add(selectedPart);
//        allParts = FXCollections.observableList( allParts
//                .stream()
//                .map(obj -> obj.getId().equals(selectedPart.getId()) ? selectedPart : obj)
//                .collect(Collectors.toCollection(ArrayList<Part>::new)));
    }

    public static void updateProduct(Integer index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }

    public static void updateProduct(Product selectedProduct){
        Product toRemove = lookupProduct(selectedProduct.getId()).get();
        allProducts.remove(toRemove);
        allProducts.add(selectedProduct);
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

    public static void addAssociation(Product product, Part part){
        if(!productPartMap.containsKey(product))
            productPartMap.put(product, FXCollections.observableArrayList());

        productPartMap.get(product).add(part);
    }

    public static void addAssociation(Product product, ObservableList<Part> list){
        if(!productPartMap.containsKey(product)) {
            productPartMap.put(product, FXCollections.observableArrayList());
            productPartMap.get(product).addAll(list);
        }
        else
            productPartMap.replace(product, list);


    }

    public static void removeAssociation(Product product, Part part){
        productPartMap.get(product).remove(part);
    }

    public static ObservableList<Part> getAssociation(Product product){
        return productPartMap.get(product);
    }
}
