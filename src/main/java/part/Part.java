package part;

import javafx.beans.property.*;

import java.util.Objects;

public abstract class Part {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty stock = new SimpleIntegerProperty();
    private IntegerProperty min = new SimpleIntegerProperty();
    private IntegerProperty max = new SimpleIntegerProperty();

    Part(Integer id, String name, Double price, Integer stock, Integer min, Integer max){
        this.id.set(id);
        this.name.set(name);
        this.price.set(price);
        this.stock.set(stock);
        this.min.set(min);
        this.max.set(max);
    }

    public StringProperty nameProperty() {return name;}

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }

    public Integer getStock() {
        return stock.get();
    }

    public void setStock(Integer stock) {
        this.stock.set(stock);
    }

    public Integer getMin() {
        return min.get();
    }

    public void setMin(Integer min) {
        this.min.set(min);
    }

    public Integer getMax() {
        return max.get();
    }

    public void setMax(Integer max) {
        this.max.set(max);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Part part = (Part) o;
//        return id.equals(part.id) &&
//                Objects.equals(name, part.name) &&
//                Objects.equals(price, part.price) &&
//                Objects.equals(stock, part.stock) &&
//                Objects.equals(min, part.min) &&
//                Objects.equals(max, part.max);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(id, part.id) &&
                Objects.equals(name, part.name) &&
                Objects.equals(price, part.price) &&
                Objects.equals(stock, part.stock) &&
                Objects.equals(min, part.min) &&
                Objects.equals(max, part.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, stock, min, max);
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
