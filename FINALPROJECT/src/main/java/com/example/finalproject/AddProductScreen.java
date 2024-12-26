package com.example.finalproject;

import Entity.Database;
import Entity.item;
import Entity.Category;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddProductScreen extends Application {

    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("Product Name:");
        TextField nameField = new TextField();

        Label stockLabel = new Label("Stock:");
        TextField stockField = new TextField();

        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();

        Label categoryLabel = new Label("Category:");
        ComboBox<String> categoryComboBox = new ComboBox<>();

        // Initialize the database
        Database.create();

        // Populate the ComboBox with categories from the database
        for (Category category : Database.categories) {
            categoryComboBox.getItems().add(category.getName());
        }

        Label imagePathLabel = new Label("Image Path:");
        TextField imagePathField = new TextField();

        Button addButton = new Button("Add Product");
        addButton.setOnAction(event -> {
            String name = nameField.getText();
            int stock = Integer.parseInt(stockField.getText());
            double price = Double.parseDouble(priceField.getText());
            String categoryName = categoryComboBox.getValue();
            String imagePath = imagePathField.getText();

            // Find the Category object by name
            Category category = Database.categories.stream()
                    .filter(cat -> cat.getName().equals(categoryName))
                    .findFirst()
                    .orElse(null);

            if (category != null) {
                // Create a new product item
                item newItem = new item(name, price, category, imagePath);

                // Add the product to the database
                Database.items.add(newItem);

                // Print confirmation
                System.out.println("Product added: " + name + ", Stock: " + stock + ", Price: " + price + ", Category: " + categoryName + ", Image Path: " + imagePath);
            } else {
                System.out.println("Category not found: " + categoryName);
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

        VBox vbox = new VBox(10, nameLabel, nameField, stockLabel, stockField, priceLabel, priceField, categoryLabel, categoryComboBox, imagePathLabel, imagePathField, addButton, backButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Add Product");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}