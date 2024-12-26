package Entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<item> items;
    private String paymentMethod;
    private double totalCost = 0.0;
    private static final double DELIVERY_FEE = 75.0;

    public Order(int orderId, Customer customer, List<item> items) {
        for (item currentItem : items) {
            totalCost += currentItem.getPrice();
        }

        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>(items);
        this.paymentMethod = "Not selected";
        this.totalCost = totalCost;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        System.out.println("Payment Method: " + paymentMethod);
        return paymentMethod;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<String> getItems() {
        List<String> itemNames = new ArrayList<>();
        for (item currentItem : items) {
            itemNames.add(currentItem.getName());
        }
        return itemNames;
    }

    public double getTotalCost() {
        return totalCost;
    }
}