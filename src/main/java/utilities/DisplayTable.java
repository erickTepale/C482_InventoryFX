package utilities;

import main.Inventory;
import part.InHouse;
import part.Outsourced;
import product.Product;


public class DisplayTable {
    public static void loadPrelimData(){
        Product product1 = new Product(1, "Product 1", 5.00, 5, 1, 5);
        Product product2 = new Product(2, "Product 2", 10.00, 10, 1, 15);
        Product product3 =new Product(3, "Product 3", 15.00, 12, 1, 20);
        Product product4 = new Product(4, "Product 4", 20.00, 5, 1, 5);


        InHouse part1 = new InHouse(4, "Part 4", 10.00, 5, 1, 5, 100);
        Outsourced part2 = new Outsourced(5, "Part 5", 10.50, 5, 1, 5, "Company 2");

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        Inventory.addPart(new InHouse(1, "Part 1", 5.00, 5, 1, 5, 100));
        Inventory.addPart(new Outsourced(2, "Part 2", 10.00, 5, 1, 15, "Company 1"));
        Inventory.addPart(new InHouse(3, "Part 3", 15.00, 5, 1, 20, 101));
        Inventory.addPart(part1);
        Inventory.addPart(part2);

        Inventory.addAssociation(product1, part1);
        Inventory.addAssociation(product2, part2);
        Inventory.addAssociation(product3, part1);
        Inventory.addAssociation(product4, part1);
        Inventory.addAssociation(product4, part2);
    }

}
