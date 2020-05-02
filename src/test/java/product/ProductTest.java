package product;

import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import part.Outsourced;
import part.Part;

public class ProductTest {

    @Test
    public void addAssociatedPartTest1(){
        Product product = new Product(1, "testProduct", 2.99, 1, 3, 4);
        product.addAssociatedPart(new Outsourced(1, "part1", 3.99, 3, 4, 5, "Epix"));
        product.addAssociatedPart(new Outsourced(2, "part2", 5.99, 3, 4, 5, "Los Santos Customs"));

        ObservableList<Part> parts = product.getAllAssociatedParts();

        Assert.assertEquals(2, parts.size());
    }

    @Test
    public void addAssociatedPartTest2(){
        Product product = new Product(1, "testProduct", 2.99, 1, 3, 4);
        Part epix = new Outsourced(1, "part1", 3.99, 3, 4, 5, "Epix");
        product.addAssociatedPart(epix);
        product.addAssociatedPart(new Outsourced(2, "part2", 5.99, 3, 4, 5, "Los Santos Customs"));


        ObservableList<Part> parts = product.getAllAssociatedParts();

        Assert.assertEquals(2, parts.size());
    }

    @Test
    public void deleteAssociatedPartTest1(){
        Product product = new Product(1, "testProduct", 2.99, 1, 3, 4);
        Part epix = new Outsourced(1, "part1", 3.99, 3, 4, 5, "Epix");
        product.addAssociatedPart(epix);
        product.addAssociatedPart(new Outsourced(2, "part2", 5.99, 3, 4, 5, "Los Santos Customs"));

        product.deleteAssociatedPart(epix);

        ObservableList<Part> parts = product.getAllAssociatedParts();

        Assert.assertEquals(1, parts.size());
    }

    @Test
    public void getAssociatedPartTest1(){
        Product product = new Product(1, "testProduct", 2.99, 1, 3, 4);

        ObservableList<Part> parts = product.getAllAssociatedParts();

        Assert.assertEquals(0, parts.size());

    }

    @Test
    public void getAssociatedPartTest2(){
        Product product = new Product(1, "testProduct", 2.99, 1, 3, 4);
        product.addAssociatedPart(new Outsourced(1, "part1", 3.99, 3, 4, 5, "Epix")) ;

        ObservableList<Part> parts = product.getAllAssociatedParts();

        Assert.assertEquals(1, parts.size());

    }
}
