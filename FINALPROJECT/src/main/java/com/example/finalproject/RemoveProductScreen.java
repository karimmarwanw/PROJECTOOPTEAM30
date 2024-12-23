package com.example.finalproject;

import Entity.Database;
import Entity.item;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoveProductScreen extends Application {

    @Override
    public void start(Stage stage) {
        Label productLabel = new Label("Select Product to Remove:");
        ComboBox<String> productComboBox = new ComboBox<>();

        // Initialize the database
        Database.create();

        // Populate the ComboBox with items from the database
        for (item product : Database.items) {
            productComboBox.getItems().add(product.getName());
        }

        Button removeButton = new Button("Remove Product");
        removeButton.setOnAction(event -> {
            String selectedProduct = productComboBox.getValue();
            if (selectedProduct != null) {
                Database.items.removeIf(product -> product.getName().equals(selectedProduct));
                System.out.println("Product removed: " + selectedProduct);
                productComboBox.getItems().remove(selectedProduct);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            AdminScreen adminScreen = new AdminScreen();
            try {
                adminScreen.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox vbox = new VBox(10, productLabel, productComboBox, removeButton, backButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Remove Product");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}