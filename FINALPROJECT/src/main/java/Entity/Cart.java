// Cart.java
package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {

    private List<item> items;
    private double totalPrice;
    private int totalQuantity;

    public Cart() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
        this.totalQuantity = 0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void addItem(item item) {
        items.add(item);
        totalPrice += item.getPrice();
        totalQuantity++;
        System.out.println(item.getName() + " added to the cart.");
    }

    public List<item> getItems() {
        return items;
    }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }

        System.out.println("Cart Contents:");
        for (item currentItem : items) {
            System.out.println("- " + currentItem.getName() + " - $" + currentItem.getPrice());
        }
        System.out.println("Total Items: " + totalQuantity);
        System.out.println("Total Price: $" + totalPrice);
    }

    public enum PaymentMethod {
        CASH,
        CARD
    }

    public void choosePaymentMethodAndPay(Customer customer) {
        if (items.isEmpty()) {
            System.out.println("The cart is empty. Add items before proceeding to payment.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose payment method (1 for Cash, 2 for Card): ");
        int choice = scanner.nextInt();

        PaymentMethod method;

        if (choice == 1) {
            method = PaymentMethod.CASH;
        } else if (choice == 2) {
            method = PaymentMethod.CARD;
        } else {
            System.out.println("Invalid choice. Defaulting to Cash.");
            method = PaymentMethod.CASH;
        }

        if (method == PaymentMethod.CASH) {
            double totalWithFee = totalPrice + 75.0;
            System.out.println("Total with delivery fee: $" + totalWithFee);
            System.out.println("Payment successful using Cash.");
        } else if (method == PaymentMethod.CARD) {
            if (customer.getBalance() >= totalPrice) {
                customer.setBalance(customer.getBalance() - totalPrice);
                System.out.println("Total: $" + totalPrice);
                System.out.println("Payment successful using Card.");
            } else {
                System.out.println("Insufficient balance. Payment failed.");
                return;
            }
        }

        Order newOrder = new Order(Database.orders.size() + 1, customer, new ArrayList<>(items));
        Database.orders.add(newOrder);
        System.out.println("Order added to database with Order ID: " + newOrder.getOrderId());

        clearCart();
    }

    public void clearCart() {
        items.clear();
        totalPrice = 0.0;
        totalQuantity = 0;
        System.out.println("Cart cleared.");
    }

    public void addItemToCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Items:");
        for (int i = 0; i < Database.items.size(); i++) {
            item currentItem = Database.items.get(i);
            System.out.println((i + 1) + ". " + currentItem.getName() + " - $" + currentItem.getPrice());
        }
        System.out.print("Enter the number of the item to add to the cart: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > Database.items.size()) {
            System.out.println("Invalid choice. No item added to the cart.");
            return;
        }

        item selectedItem = Database.items.get(choice - 1);
        System.out.print("Enter the quantity to add to the cart: ");
        int quantity = scanner.nextInt();

        if (quantity > selectedItem.getStock()) {
            System.out.println("Invalid quantity. No item added to the cart.");
            return;
        }

        for (int i = 0; i < quantity; i++) {
            addItem(selectedItem);
        }
        selectedItem.setStock(selectedItem.getStock() - quantity);
        System.out.println(quantity + " " + selectedItem.getName() + "(s) added to the cart.");
    }

    public void removeItemFromCart() {
        if (items.isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Cart Contents:");
        for (int i = 0; i < items.size(); i++) {
            item currentItem = items.get(i);
            System.out.println((i + 1) + ". " + currentItem.getName() + " - $" + currentItem.getPrice());
        }
        System.out.print("Enter the number of the item to remove from the cart: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > items.size()) {
            System.out.println("Invalid choice. No item removed from the cart.");
            return;
        }

        item removedItem = items.remove(choice - 1);
        totalPrice -= removedItem.getPrice();
        totalQuantity--;
        removedItem.setStock(removedItem.getStock() + 1); // Update the stock
        System.out.println("Removed " + removedItem.getName() + " from the cart.");
    }
}