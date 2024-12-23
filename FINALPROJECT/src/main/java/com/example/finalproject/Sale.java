package com.example.finalproject;


import Entity.Database;
import Entity.Order;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

public class Sale extends Application {


    @Override
    public void start(Stage primaryStage) {
        Database.create();

        // Get screen dimensions
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // Create the sale title
        Text catchSaleTitle = new Text("CATCH OUR MEGA SALE!!!!");
        catchSaleTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        catchSaleTitle.setFill(Color.WHITE);
        catchSaleTitle.setWrappingWidth(screenWidth - 100); // Dynamically adjust based on screen width
        catchSaleTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Rectangle titleBackground = new Rectangle(screenWidth, 60); // Adjust background to fit screen width
        titleBackground.setFill(Color.web("red"));

        StackPane catchSalePane = new StackPane(titleBackground, catchSaleTitle);
        catchSalePane.setAlignment(Pos.CENTER);

        // Initialize the database

        // GridPane to hold the product items
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        int row = 0;
        int col = 0;

        // Dynamically create UI elements for each product on sale
        for (ProductSale productSale : Database.sale) {
            // Create product components
            ImageView productImageView;
            try {
                productImageView = new ImageView(new Image(productSale.getImagePath())); // Instance method
            } catch (Exception e) {
                productImageView = new ImageView(new Image(Database.defaultImagePath));
            }
            productImageView.setFitWidth(200);
            productImageView.setFitHeight(200);
            productImageView.setPreserveRatio(true);

            Text productLabel = new Text(productSale.getName()); // Instance method
            productLabel.setFont(Font.font("Arial", 16));
            productLabel.setWrappingWidth(200);
            productLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

            Text productPriceBefore = new Text("Was $" + productSale.getOriginalPrice()); // Instance method
            Text productPriceAfter = new Text("Now $" + productSale.getDiscountedPrice()); // Instance method
            productPriceBefore.setFont(Font.font("Courier New", 12));
            productPriceAfter.setFont(Font.font("Courier New", 12));

            Button addToCartButton = new Button("ADD TO CART");
            addToCartButton.setStyle("-fx-background-color: #008BAA; -fx-text-fill: black; -fx-font-weight: bold;");
            addToCartButton.setMaxWidth(200);

            // Add event handler for the button
            addToCartButton.setOnAction(e -> {
                Order newOrder = new Order();
                Database.addOrder(newOrder);
                System.out.println("Added to cart: " + productSale.getName()); // Instance method
            });

            VBox productVBox = new VBox(10, productLabel, productImageView, productPriceBefore, productPriceAfter, addToCartButton);
            productVBox.setAlignment(Pos.CENTER);

            gridPane.add(productVBox, col, row);

            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        // Add the gridPane to a ScrollPane
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox mainLayout = new VBox(20, catchSalePane, scrollPane);
        mainLayout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(mainLayout, screenWidth, screenHeight); // Set Scene dimensions dynamically
        scene.setFill(Color.WHITE);

        // Set stage to full screen
        primaryStage.setTitle("MEGA SALE");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true); // Enable full-screen mode
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}