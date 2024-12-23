package com.example.finalproject;

import Entity.Category;
import Entity.Database;
import Entity.item;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

class categoryHP extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize the Database (to load categories)
        Database.create();

        // Fetch categories from the Database.categories list
        List<Category> categories = Database.categories;

        // Create UI to display categories
        GridPane gridPane = createCategoryGrid(categories, primaryStage);

        // Scene setup
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Categories");
        primaryStage.show();
    }

    /**
     * Creates a GridPane to display the list of categories as buttons.
     */
    private GridPane createCategoryGrid(List<Category> categories, Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);   // Horizontal gap
        gridPane.setVgap(20);   // Vertical gap
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);

        int column = 0;
        int row = 0;

        for (Category category : categories) {
            // Load Category Image
            Image image;
            try {
                image = new Image("file:" + category.getImagePath());
            } catch (Exception e) {
                image = new Image("file:src/products/default.jpg");
                System.out.println("Error loading image for category: " + category.getName());
            }

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);

            // Category Button
            Button categoryButton = new Button(category.getName());
            categoryButton.setGraphic(imageView);
            categoryButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            categoryButton.setOnAction(event -> showProductsByCategory(primaryStage, category));

            // Add to GridPane
            gridPane.add(categoryButton, column, row);

            // Update column and row for next category
            column++;
            if (column == 3) { // Move to next row after 3 items
                column = 0;
                row++;
            }
        }

        return gridPane;
    }

    /**
     * Displays products of a specific category.
     */
    private void showProductsByCategory(Stage primaryStage, Category category) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Label categoryLabel = new Label("Category: " + category.getName());
        categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        layout.getChildren().add(categoryLabel);

        // Fetch and display products belonging to the category
        List<item> products = Database.items.stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();

        for (item product : products) {
            VBox productBox = new VBox(5);
            productBox.setAlignment(Pos.CENTER);

            // Product Image
            Image image;
            try {
                image = new Image(product.getImagePath());
            } catch (Exception e) {
                image = new Image("file:src/products/default.jpg");
                System.out.println("Error loading image for product: " + product.getName());
            }

            ImageView productImageView = new ImageView(image);
            productImageView.setFitWidth(100);
            productImageView.setFitHeight(100);
            productImageView.setPreserveRatio(true);

            Label productNameLabel = new Label(product.getName() + " - EGP " + product.getPrice());

            productBox.getChildren().addAll(productImageView, productNameLabel);

            layout.getChildren().add(productBox);
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> start(primaryStage));

        layout.getChildren().add(backButton);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
