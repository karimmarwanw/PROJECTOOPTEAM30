package Entity;

import java.util.ArrayList;
import java.util.List;


public class Category {
    private String name;
    private String description;
    private ArrayList<item> products;
    public  String imagePath;
    // private ArrayList<ProductSale> productSales;

    public Category() {
    }

    public Category(String name, String description,String imagePath) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
        this.imagePath=imagePath;
    }

    public List<item> getItems() {
        return products;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<item> getProducts() {
        return products;
    }

    /**
    public ArrayList<ProductSale> getProductSales() {
        return productSales;
    }
    */

    public void addProduct(item product) {
        products.add(product);
    }


    public void removeProduct(item product) {
        if (products.contains(product)) {
            products.remove(product);
        } else {
            System.out.println("Product not found in category.");
        }
    }

    public void listProducts() {
        System.out.println("Products in category: " + name);
        for (item product : products) {
            System.out.println(product);
        }
    }

    @Override
    public String toString() {
        return "Category: " + name + " - " + description;
    }
}