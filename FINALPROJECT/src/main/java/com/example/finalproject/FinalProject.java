// FinalProject.java
// main screen for customers
package com.example.finalproject;

import Entity.Cart;
import Entity.Category;
import Entity.Customer;
import Entity.Database;
import Entity.item;
import Entity.Gender;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class FinalProject extends Application {
    private Scene mainScene; // Global reference to the main scene
    private Cart cart = new Cart(); // Global reference to the cart
    private Customer customer; // Global reference to the customer

    @Override
    public void start(Stage stage) {
        Database.create(); // Initialize database

        // Create a Customer object
        customer = new Customer("username", "password", "01-01-2000", 1000.0, "123 Main St", Gender.MALE, null, cart);

        // Get screen dimensions
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // ------------------- Banner Image ---------------------
        Image bannerImage = new Image("file:D:\\Uni\\OOP\\FINALPROJECT\\src\\theme\\360_F_482004517_6duaJUqQeaJFtgWSIeT2D4kcyixmauoE.jpg");
        ImageView bannerImageView = new ImageView(bannerImage);
        bannerImageView.setFitWidth(screenWidth);
        bannerImageView.setFitHeight(screenHeight * 0.2);
        bannerImageView.setPreserveRatio(false);

        // ------------------- Center Image ---------------------
        Image centerImage = new Image("file:D:\\Uni\\OOP\\FINALPROJECT\\src\\theme\\mega no bg.png");
        ImageView centerImageView = new ImageView(centerImage);
        centerImageView.setFitWidth(screenWidth * 0.1);
        centerImageView.setFitHeight(screenHeight * 0.1);
        centerImageView.setPreserveRatio(true);

        // ------------------- Search Bar ---------------------
        ComboBox<String> searchBar = new ComboBox<>();
        searchBar.setEditable(true);
        searchBar.setPromptText("Search for a product...");
        searchBar.setPrefWidth(screenWidth * 0.5);
        searchBar.setStyle(
                "-fx-font-size: 14px; " +
                        "-fx-background-color: white; " +
                        "-fx-border-radius: 10; " +
                        "-fx-background-radius: 10; " +
                        "-fx-padding: 10;"
        );

        searchBar.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (searchBar.isShowing()) return;
            String searchTerm = newValue.toLowerCase().trim();
            searchBar.getItems().clear();
            if (!searchTerm.isEmpty()) {
                Database.items.stream()
                        .filter(product -> product.getName().toLowerCase().contains(searchTerm))
                        .map(product -> product.getName() + " - EGP" + product.getPrice())
                        .distinct()
                        .forEach(searchBar.getItems()::add);
                if (searchBar.getItems().isEmpty()) {
                    searchBar.getItems().add("No products found.");
                }
            }
        });

        searchBar.setOnAction(e -> {
            String selectedItem = searchBar.getSelectionModel().getSelectedItem();
            if (selectedItem != null && !selectedItem.equals("No products found.")) {
                String productName = selectedItem.split(" - ")[0].trim();

                item selectedProduct = Database.items.stream()
                        .filter(it -> it.getName().equalsIgnoreCase(productName))
                        .findFirst()
                        .orElse(null);

                if (selectedProduct != null) {
                    showProductDetailsScreen(stage, selectedProduct);
                } else {
                    System.out.println("Product not found: " + productName);
                }
            }
        });

        // ------------------- Logout Button ---------------------
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> {
            LoginController loginController = new LoginController();
            try {
                loginController.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // ------------------- See Cart Button ---------------------
        Button seeCartButton = new Button("See Cart");
        seeCartButton.setOnAction(event -> {
            CartScreen cartScreen = new CartScreen(cart, stage, customer);
            try {
                cartScreen.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // ------------------- Category Grid ---------------------
        List<Category> categories = Database.categories;
        GridPane categoryGrid = createCategoryGrid(categories, stage);

        // ------------------- Layout Setup ---------------------
        StackPane bannerPane = new StackPane();
        bannerPane.getChildren().addAll(bannerImageView, centerImageView);

        VBox searchBox = new VBox(10);
        searchBox.setAlignment(Pos.TOP_LEFT);
        searchBox.setPadding(new Insets(10));
        searchBox.getChildren().addAll(searchBar, logoutButton, seeCartButton);

        VBox root = new VBox(10);
        root.getChildren().addAll(bannerPane, searchBox, categoryGrid);

        mainScene = new Scene(root, screenWidth, screenHeight);
        stage.setTitle("MEGA STORE");
        stage.setScene(mainScene);
        stage.show();
    }

    private GridPane createCategoryGrid(List<Category> categories, Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);

        int column = 0;
        int row = 0;

        for (Category category : categories) {
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

            Button categoryButton = new Button(category.getName());
            categoryButton.setGraphic(imageView);
            categoryButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            categoryButton.setOnAction(event -> showCategoryProducts(stage, category));

            gridPane.add(categoryButton, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        return gridPane;
    }

    private void showCategoryProducts(Stage stage, Category category) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Label categoryLabel = new Label("Category: " + category.getName());
        categoryLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        layout.getChildren().add(categoryLabel);

        GridPane productGrid = new GridPane();
        productGrid.setHgap(20);
        productGrid.setVgap(20);
        productGrid.setAlignment(Pos.CENTER);

        List<item> products = Database.items.stream()
                .filter(product -> product.getCategory() != null && product.getCategory().equals(category))
                .toList();

        int column = 0;
        int row = 0;

        for (item product : products) {
            VBox productBox = new VBox(5);
            productBox.setAlignment(Pos.CENTER);

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

            Button addToCartButton = Database.createAddToCartButton(product, cart);

            productBox.getChildren().addAll(productImageView, productNameLabel, addToCartButton);
            productGrid.add(productBox, column, row);

            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> start(stage));

        layout.getChildren().addAll(productGrid, backButton);

        Scene categoryScene = new Scene(layout, stage.getWidth(), stage.getHeight());
        stage.setScene(categoryScene);
    }

    void showProductDetailsScreen(Stage stage, item product) {
        Image image;
        try {
            image = new Image(product.getImagePath());
        } catch (Exception e) {
            image = new Image("file:src/products/default.jpg");
            System.out.println("Error loading image for product: " + product.getName());
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        Label descriptionLabel = new Label(product.getName());
        Label priceLabel = new Label("Price: EGP " + product.getPrice());

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(event -> {
            cart.addItem(product);
            System.out.println("Added to cart: " + product.getName());
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> start(stage));

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(imageView, descriptionLabel, priceLabel, addToCartButton, backButton);

        Scene productDetailsScene = new Scene(layout, stage.getWidth(), stage.getHeight());
        stage.setScene(productDetailsScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}