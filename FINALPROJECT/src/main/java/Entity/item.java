package Entity;

public class item {
    private String name;
    private int stock;
    private int id;
    private Category category;
    private double price;
    private static int counter = 1;
    private String imagePath;

    public item(String name, int stock, String categoryName, Category category, String imagePath) {
        this.id = counter++;
        this.name = name;
        this.price = 0.0;
        this.stock = stock;
        this.category = category;
        this.imagePath = imagePath;
    }

    public item(String name, double price, Category category, String imagePath) {
        this.id = counter++;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = 10;
        this.imagePath = imagePath;
    }

    public item(String name, double price, String categoryName, String imagePath) {
        this.id = counter++;
        this.name = name;
        this.price = price;
        this.stock = 10;
        this.imagePath = imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Error: Price cannot be negative");
        } else {
            this.price = price;
        }
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            System.out.println("Error: Stock cannot be negative");
        } else {
            this.stock = stock;
        }
    }

    public int getStock() {
        return stock;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", stock=" + stock +
                '}';
    }
}
