package com.example.finalproject;

public class ProductSale {
    private String id;
    private  String name;
    private String description;
    private int stockQuantity;
    private String category;
    private  String imagePath;
    private  double originalPrice;
    private  double discountedPrice;

    // Constructor
    public ProductSale( String name, String category, String imagePath, double originalPrice, double discountedPrice) {
        this.name = name;
        this.category = category;
        this.imagePath = imagePath;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
    }

    // Default constructor
    public ProductSale() {
    }


    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        } else {
            System.out.println("Stock quantity cannot be negative.");
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }


    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        if (originalPrice > 0) {
            this.originalPrice = originalPrice;
        } else {
            System.out.println("Original price must be greater than 0.");
        }
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        if (discountedPrice > 0) {
            this.discountedPrice = discountedPrice;
        } else {
            System.out.println("Discounted price must be greater than 0.");
        }
    }

    // toString method for better object representation
    @Override
    public String toString() {
        return "Product ID: " + id +
                ", Name: " + name +
                ", Description: " + description +
                ", Original Price: " + originalPrice +
                " EGP, Discounted Price: " + discountedPrice +
                " EGP, Stock: " + stockQuantity +
                ", Image Path: " + imagePath;
    }

    public static void add(ProductSale productSales) {
    }
}
