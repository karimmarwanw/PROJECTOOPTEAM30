package com.example.finalproject;

import Entity.Cart;
import Entity.Customer;
import Entity.item;
import Entity.Order;
import Entity.Database;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class CartScreen extends Application {
    private Cart cart;
    private Stage primaryStage;
    private Customer customer;

    public CartScreen(Cart cart, Stage primaryStage, Customer customer) {
        this.cart = cart;
        this.primaryStage = primaryStage;
        this.customer = customer;
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label cartLabel = new Label("Your Cart");
        cartLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        List<item> items = cart.getItems();
        GridPane gridPane = createCartGrid(items);

        Label totalLabel = new Label("Total: $" + calculateTotal(items));
        totalLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            primaryStage.show();
            stage.close();
        });

        ComboBox<String> paymentMethodComboBox = new ComboBox<>();
        paymentMethodComboBox.getItems().addAll("Credit Card", "Cash on Delivery");
        paymentMethodComboBox.setPromptText("Select Payment Method");

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setOnAction(event -> {
            String selectedPaymentMethod = paymentMethodComboBox.getValue();
            if (selectedPaymentMethod != null) {
                handleCheckout(selectedPaymentMethod);
                primaryStage.show();
                stage.close();
            } else {
                System.out.println("Please select a payment method.");
            }
        });

        root.getChildren().addAll(cartLabel, gridPane, totalLabel, paymentMethodComboBox, backButton, checkoutButton);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Cart");
        stage.show();
    }

    private GridPane createCartGrid(List<item> items) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);

        int column = 0;
        int row = 0;

        for (item item : items) {
            Label nameLabel = new Label(item.getName());
            nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label priceLabel = new Label("Price: $" + item.getPrice());
            priceLabel.setStyle("-fx-font-size: 14px;");

            VBox itemBox = new VBox(10);
            itemBox.setAlignment(Pos.CENTER);
            itemBox.getChildren().addAll(nameLabel, priceLabel);

            gridPane.add(itemBox, column, row);

            column++;
            if (column == 2) {
                column = 0;
                row++;
            }
        }

        return gridPane;
    }

    private double calculateTotal(List<item> items) {
        return items.stream().mapToDouble(item::getPrice).sum();
    }

  private void handleCheckout(String paymentMethod) {
    List<item> items = cart.getItems();
    customer.getOrders().addAll(items);

    // Create a new Order object
    int orderId = Database.orders.size() + 1; // Generate a new order ID
    Order order = new Order(orderId, customer, items);
    order.setPaymentMethod(paymentMethod); // Set the payment method

    Database.orders.add(order);
    cart.clearCart();
    System.out.println("Checkout completed with payment method: " + paymentMethod);
}

    public static void main(String[] args) {
        launch(args);
    }
}