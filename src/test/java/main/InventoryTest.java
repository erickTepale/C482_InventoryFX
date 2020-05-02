package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import part.InHouse;
import part.Outsourced;
import part.Part;
import product.Product;

import java.util.Optional;

public class InventoryTest {

    @Before
    public void testSetup(){
        Inventory.allProducts = FXCollections.observableArrayList();
        Inventory.allParts = FXCollections.observableArrayList();
    }

    @Test
    public void addPartTest1(){
        Inventory.addPart(new Outsourced(1,"bleh", 3.44, 3,2,3, "newCompany"));

        Assert.assertEquals(1, Inventory.allParts.size());
    }

    @Test
    public void lookupPartId1(){
        Inventory.addPart(new Outsourced(1,"bleh", 3.44, 3,2,3, "newCompany"));

        Optional<Part> part = Inventory.lookupPart(1);
        Assert.assertEquals("bleh", part.get().getName());
    }

    @Test
    public void lookupPartId2(){
        Inventory.addPart(new Outsourced(1,"bleh", 3.44, 3,2,3, "newCompany"));
        Inventory.addPart(new InHouse(2, "oh", 400.0, 2, 1, 3, 223432));

        Optional<Part> part = Inventory.lookupPart(2);
        Assert.assertEquals("oh", part.get().getName());
    }

    @Test
    public void lookupPartId3(){
        Inventory.addPart(new Outsourced(1,"bleh", 3.44, 3,2,3, "newCompany"));
        Inventory.addPart(new InHouse(2, "oh", 400.0, 2, 1, 3, 223432));

        Optional<Part> part = Inventory.lookupPart(3);
        System.out.println(part);
        Assert.assertEquals(Optional.empty(), part);
    }

    @Test
    public void lookupPartName1(){
        Inventory.addPart(new Outsourced(1,"bleh", 3.44, 3,2,3, "newCompany"));
        Inventory.addPart(new InHouse(2, "oh", 400.0, 2, 1, 3, 223432));

        ObservableList<Part> part = Inventory.lookupPart("oh");
        Assert.assertEquals(1, part.size());
    }

    @Test
    public void lookupPartName2(){
        Inventory.addPart(new Outsourced(1,"bleh", 3.44, 3,2,3, "newCompany"));
        Inventory.addPart(new InHouse(2, "oh", 400.0, 2, 1, 3, 223432));
        Inventory.addPart(new Outsourced(3,"bleh", 3.44, 3,2,3, "bjfh"));
        Inventory.addPart(new InHouse(4, "oh", 500.0, 2, 1, 3, 564345));


        ObservableList<Part> part = Inventory.lookupPart("oh");
        Assert.assertEquals(2, part.size());
    }

    @Test
    public void lookupPartName3(){
        Inventory.addPart(new Outsourced(1,"bleh", 3.44, 3,2,3, "newCompany"));
        Inventory.addPart(new InHouse(2, "oh", 400.0, 2, 1, 3, 223432));
        Inventory.addPart(new Outsourced(3,"bleh", 3.44, 3,2,3, "bjfh"));
        Inventory.addPart(new InHouse(4, "oh", 500.0, 2, 1, 3, 564345));


        ObservableList<Part> part = Inventory.lookupPart("sdfss");
        Assert.assertEquals(0, part.size());
    }

    @Test
    public void UpdatePart1(){
        Inventory.addPart(new Outsourced(1,"bleh", 3.44, 3,2,3, "newCompany"));
        Inventory.addPart(new InHouse(2, "oh", 400.0, 2, 1, 3, 223432));
        Inventory.addPart(new Outsourced(3,"bleh", 3.44, 3,2,3, "bjfh"));

        Part update = new InHouse(4, "blurb", 500.0, 2, 1, 3, 564345);

        Inventory.updatePart(1, update);

        Assert.assertEquals("blurb", Inventory.getAllParts().get(1).getName());
    }

    @Test
    public void DeletePart1(){
        Inventory.addPart(new Outsourced(1,"bleh", 3.44, 3,2,3, "newCompany"));
        Inventory.addPart(new InHouse(2, "oh", 400.0, 2, 1, 3, 223432));
        Inventory.addPart(new Outsourced(3,"bleh", 3.44, 3,2,3, "bjfh"));

        Part delete = new InHouse(4, "blurb", 500.0, 2, 1, 3, 564345);
        Inventory.addPart(delete);

        Inventory.deletePart(delete);

        Assert.assertEquals(Optional.empty(), Inventory.lookupPart(delete.getId()));
    }

    @Test
    public void addProductTest1(){
        Inventory.addProduct(new Product(1, "prod", 3.99, 3,2,4));

        Assert.assertEquals(1, Inventory.allProducts.size());
    }

    @Test
    public void lookupProductId1(){
        Inventory.addProduct(new Product(1, "prod", 3.99, 3,2,4));

        Optional<Product> product = Inventory.lookupProduct(1);
        Assert.assertEquals("prod", product.get().getName());
    }

    @Test
    public void lookupProductId2(){
        Inventory.addProduct(new Product(1, "prod", 3.99, 3,2,4));
        Inventory.addProduct(new Product(2, "product", 3.99, 3,2,4));

        Optional<Product> product = Inventory.lookupProduct(2);
        Assert.assertEquals("product", product.get().getName());
    }

    @Test
    public void lookupProductId3(){
        Inventory.addProduct(new Product(1, "prod", 3.99, 3,2,4));
        Inventory.addProduct(new Product(2, "product", 3.99, 3,2,4));

        Optional<Product> product = Inventory.lookupProduct(3);
        System.out.println(product);
        Assert.assertEquals(Optional.empty(), product);
    }

    @Test
    public void lookupProductName1(){
        Inventory.addProduct(new Product(1, "prod", 3.99, 3,2,4));
        Inventory.addProduct(new Product(2, "product", 3.99, 3,2,4));

        ObservableList<Product> product = Inventory.lookupProduct("product");

        Assert.assertEquals(1, product.size());
    }

    @Test
    public void lookupProductName2(){
        Inventory.addProduct(new Product(1, "prod", 3.99, 3,2,4));
        Inventory.addProduct(new Product(2, "product", 3.99, 3,2,4));
        Inventory.addProduct(new Product(3, "eat", 3.99, 3,2,4));
        Inventory.addProduct(new Product(4, "product", 6.99, 7,3,18));

        ObservableList<Product> product = Inventory.lookupProduct("product");
        Assert.assertEquals(2, product.size());
    }

    @Test
    public void lookupProductName3(){
        Inventory.addProduct(new Product(1, "prod", 3.99, 3,2,4));
        Inventory.addProduct(new Product(2, "product", 3.99, 3,2,4));
        Inventory.addProduct(new Product(3, "eat", 3.99, 3,2,4));
        Inventory.addProduct(new Product(4, "product", 6.99, 7,3,18));

        ObservableList<Product> product = Inventory.lookupProduct("west");
        Assert.assertEquals(0, product.size());
    }

    @Test
    public void updateProduct1(){
        Inventory.addProduct(new Product(1, "prod", 3.99, 3,2,4));
        Inventory.addProduct(new Product(2, "product", 3.99, 3,2,4));
        Product update = new Product(3, "updated", 4.99, 3, 1, 5);

        Inventory.updateProduct(0, update);

        Assert.assertEquals("updated", Inventory.getAllProducts().get(0).getName());
    }

    @Test
    public void deleteProduct1(){
        Inventory.addProduct(new Product(1, "prod", 3.99, 3,2,4));
        Inventory.addProduct(new Product(2, "product", 3.99, 3,2,4));
        Product delete = new Product(3, "updated", 4.99, 3, 1, 5);
        Inventory.addProduct(delete);

        Inventory.deleteProduct(delete);


        Assert.assertEquals(Optional.empty(), Inventory.lookupProduct(delete.getId()));
    }
}
