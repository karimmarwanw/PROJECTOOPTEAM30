package Entity;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {

    private double balance;
    private String address;
    private Gender gender;
    private List<String> interests;
    private Cart cart;
    private List<item> orders;


    public Customer(String username, String password, String dateOfBirth, double balance,
                    String address, Gender gender, List<String> interests, Cart cart) {
        super(username, password, dateOfBirth);
        this.balance = balance;
        this.address = address;
        this.gender = gender;
        this.interests = interests != null ? new ArrayList<>(interests) : new ArrayList<>();
        this.cart = cart != null ? cart : new Cart();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Balance cannot be negative.");
        }
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<String> getInterests() {
        return new ArrayList<>(interests);
    }

    public void addInterest(String interest) {
        if (interest != null && !interests.contains(interest)) {
            interests.add(interest);
        }
    }

    public void removeInterest(String interest) {
        interests.remove(interest);
    }

    public void displayRole() {
        System.out.println("Role: Customer");
    }

    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", balance=" + balance +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", interests=" + interests +
                '}';
    }

    public void displayAllCart() {
        cart.showCart();
    }

    public Cart getCart() {
        return cart;
    }

    public void addItemToCart(item item) {
        cart.addItem(item);
    }

    public List<item> getCartItems() {
        return cart.getItems();
    }


    public List<item> getOrders() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }

    public String getUsername() {
        return username;
    }

}