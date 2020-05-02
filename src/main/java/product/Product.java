package product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import part.Part;

public class Product {
    protected ObservableList<Part> associatedParts;
    private Integer id;
    private String name;
    private Double price;
    private Integer stock;
    private  Integer min;
    private  Integer max;

    public Product(Integer id, String name, Double price, Integer stock, Integer min, Integer max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = FXCollections.observableArrayList();
    }

    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part part){
        return this.associatedParts.remove(part);
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * Setter & Getters Below
     *
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Product{" +
                "associatedParts=" + associatedParts +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
