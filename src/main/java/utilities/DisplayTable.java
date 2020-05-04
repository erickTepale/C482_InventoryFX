package utilities;

import main.Inventory;
import part.InHouse;
import part.Outsourced;
import part.PartController;
import product.Product;

import javax.swing.table.TableColumn;

public class DisplayTable {
    public static void loadPrelimData(){
        Inventory.addProduct(new Product(1, "Product 1", 5.00, 5, 1, 5));
        Inventory.addProduct(new Product(2, "Product 2", 10.00, 10, 1, 15));
        Inventory.addProduct(new Product(3, "Product 3", 15.00, 12, 1, 20));

        Inventory.addPart(new InHouse(1, "Part 1", 5.00, 5, 1, 5, 100));
        Inventory.addPart(new Outsourced(2, "Part 2", 10.00, 5, 1, 15, "Company 1"));
        Inventory.addPart(new InHouse(3, "Part 3", 15.00, 5, 1, 20, 101));
    }

}
