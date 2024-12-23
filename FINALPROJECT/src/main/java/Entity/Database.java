package Entity;

import com.example.finalproject.ProductSale;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    public static List<item> items = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static List<Supplier> suppliers = new ArrayList<>();
    public static List<Category> categories = new ArrayList<>();
    public static List<Cart> carts = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<ProductSale> sale = new ArrayList<>();

    public static String defaultImagePath = "src/products/default.jpg";

    // Utility method to add an order
    public static void addOrder(Order order) {
        orders.add(order);
    }

    static Category c1 = new Category("Laptops", "devices","D:\\Uni\\OOP\\FINALPROJECT v2\\FINALPROJECT(adam)\\FINALPROJECT\\src\\products\\Laqptops.jpeg");
    static item p1 = new item("dell laptop", 1000, c1, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\images.jpeg");
    static item p2 = new item("macbook", 2000, c1, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\download (1).jpeg");

    static Category c2 = new Category("Mobiles", "smart phones","D:\\Uni\\OOP\\FINALPROJECT v2\\FINALPROJECT(adam)\\FINALPROJECT\\src\\products\\mobiles.jpg");
    static item p3 = new item("samsung", 500, c2, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\download (3).jpeg");
    static item p4 = new item("iphone", 750, c2, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\download (2).jpeg");

    static Category c3 = new Category("Mouses", "accessories","D:\\Uni\\OOP\\FINALPROJECT v2\\FINALPROJECT(adam)\\FINALPROJECT\\src\\products\\mouse.jpg");
    static item p5 = new item("HP mouse", 100, c3, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\images (1).jpeg");
    static item p6 = new item("apple mouse", 200, c3, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\download (4).jpeg");

    static Category c4 = new Category("Keyboards", "keyboards","D:\\Uni\\OOP\\FINALPROJECT v2\\FINALPROJECT(adam)\\FINALPROJECT\\src\\products\\keyboards.jpg");
    static item p7 = new item("HP keyboard", 150, c4, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\download (5).jpeg");
    static item p8 = new item("apple keyboard", 200, c4, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\download.png");

    static Category c5 = new Category("Headphones", "headphones","D:\\Uni\\OOP\\FINALPROJECT v2\\FINALPROJECT(adam)\\FINALPROJECT\\src\\products\\headphones.jpg");
    static item p9 = new item("sony headphones", 300, c5, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\download (6).jpeg");
    static item p10 = new item("apple headphones", 500, c5, "D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\download (7).jpeg");

    static Category c6 = new Category("in sale", "sale","D:\\Uni\\OOP\\FINALPROJECT\\src\\theme\\images__1_-removebg-preview.png");
    static ProductSale s1 = new ProductSale("hp laptop","Sale","D:\\Uni\\OOP\\FINALPROJECT\\src\\theme\\hp laptop.jpeg",16000,10000);
    static ProductSale s2 = new ProductSale("hp mouse","Sale","D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\mouse.jpg",5000,1000);
    static ProductSale s3 = new ProductSale("airpods","Sale","D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\airpods.jpeg",3000,2500);
    static ProductSale s4 = new ProductSale("black keyboard","Sale","D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\keyboard.jpg",2500,1500);
    static ProductSale s5 = new ProductSale("black keyboard","Sale","D:\\Uni\\OOP\\FINALPROJECT\\src\\products\\keyboard.jpg",2500,1500);

    private static boolean isInitialized = false;

    public static void create() {
        if (isInitialized) return; // If already initialized, do nothing
        isInitialized = true;

        c1.addProduct(p1);
        c1.addProduct(p2);
        c2.addProduct(p3);
        c2.addProduct(p4);
        c3.addProduct(p5);
        c3.addProduct(p6);
        c4.addProduct(p7);
        c4.addProduct(p8);
        c5.addProduct(p9);
        c5.addProduct(p10);

        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        categories.add(c4);
        categories.add(c5);
        categories.add(c6);

        items.add(p1);
        items.add(p2);
        items.add(p3);
        items.add(p4);
        items.add(p5);
        items.add(p6);
        items.add(p7);
        items.add(p8);
        items.add(p9);
        items.add(p10);

        sale.add(s1);
        sale.add(s2);
        sale.add(s3);
        sale.add(s4);
        sale.add(s5);

        admins.add(new Admin("adam", "511", "2004-11-05", "ceo", 40));
        admins.add(new Admin("sameh", "2112", "2004-12-21", "cfo", 35));
        admins.add(new Admin("karim", "0000", "2004-12-21", "ceo", 41));
        admins.add(new Admin("fady", "1111", "2004-12-21", "ceo", 41));

        customers.add(new Customer(
                "ahmed", "111111", "2000-05-20", 1500.0, "123 Elm St",
                Gender.MALE, Arrays.asList("Electronics", "Gaming"), new Cart()));
        customers.add(new Customer(
                "emad", "222222", "1995-08-15", 800.0, "456 Oak St",
                Gender.FEMALE, Arrays.asList("Accessories", "Fashion"), new Cart()));

        suppliers.add(new Supplier(101, "supplier1", "333333", "1985-03-10", "0123456789"));
        suppliers.add(new Supplier(102, "supplier2", "444444", "1990-07-25", "0987654321"));

        Cart cart1 = new Cart();
        cart1.addItem(items.get(0));
        cart1.addItem(items.get(2));
        carts.add(cart1);

        Cart cart2 = new Cart();
        cart2.addItem(items.get(1));
        cart2.addItem(items.get(3));
        carts.add(cart2);

        System.out.println("Database initialized with dummy data.");
    }

    public static List<Category> getCategories() {
        return categories;
    }

    public static List<item> getItems() {
        return items;
    }

    public static Button createAddToCartButton(item product, Cart cart) {
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(event -> {
            cart.addItem(product);
            System.out.println("Added to cart: " + product.getName());
        });
        return addToCartButton;
    }
}