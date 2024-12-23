package com.example.finalproject;

import Entity.Database;
import Entity.Order;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrdersScreen extends Application {

    @Override
    public void start(Stage stage) {
        ListView<String> ordersListView = new ListView<>();

        // Retrieve orders from the database and display them
        for (Order order : Database.orders) {
            String orderDetails = "Order ID: " + order.getOrderId() +
                                  ", Customer: " + order.getCustomer().getUsername() +
                                  ", Total Cost: $" + order.getTotalCost() +
                                  ", Payment Method: " + order.getPaymentMethod();
            ordersListView.getItems().add(orderDetails);
        }

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(new Label("Orders"), ordersListView);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("View Orders");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}