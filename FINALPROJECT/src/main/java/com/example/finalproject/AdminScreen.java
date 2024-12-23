package com.example.finalproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminScreen extends Application {

    @Override
    public void start(Stage stage) {
        double screenWidth = 800;

        // ------------------- Banner Image ---------------------
        Image bannerImage = new Image("file:D:\\Uni\\OOP\\FINALPROJECT\\src\\theme\\360_F_482004517_6duaJUqQeaJFtgWSIeT2D4kcyixmauoE.jpg");
        ImageView bannerImageView = new ImageView(bannerImage);
        bannerImageView.setFitWidth(screenWidth);
        bannerImageView.setFitHeight(150);
        bannerImageView.setPreserveRatio(false);

        // ------------------- Center Image ---------------------
        Image centerImage = new Image("file:D:\\Uni\\OOP\\FINALPROJECT\\src\\theme\\mega no bg.png");
        ImageView centerImageView = new ImageView(centerImage);
        centerImageView.setFitWidth(150);
        centerImageView.setFitHeight(150);
        centerImageView.setPreserveRatio(true);

        // ------------------- Admin Function Buttons ---------------------
        Button addProductButton = new Button("Add Product");
        addProductButton.setOnAction(event -> {
            AddProductScreen addProductScreen = new AddProductScreen();
            try {
                addProductScreen.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button removeProductButton = new Button("Remove Product");
        removeProductButton.setOnAction(event -> {
            RemoveProductScreen removeProductScreen = new RemoveProductScreen();
            try {
                removeProductScreen.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button viewOrdersButton = new Button("View Orders");
        viewOrdersButton.setOnAction(event -> {
            OrdersScreen ordersScreen = new OrdersScreen();
            try {
                ordersScreen.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> {
            LoginController loginController = new LoginController();
            try {
                loginController.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox adminButtonsBox = new VBox(10);
        adminButtonsBox.setAlignment(Pos.CENTER);
        adminButtonsBox.setPadding(new Insets(10));
        adminButtonsBox.getChildren().addAll(addProductButton, removeProductButton, viewOrdersButton, logoutButton);

        // ------------------- Layout Setup ---------------------
        StackPane bannerPane = new StackPane();
        bannerPane.getChildren().addAll(bannerImageView, centerImageView);

        VBox root = new VBox(10);
        root.getChildren().addAll(bannerPane, adminButtonsBox);

        // ------------------- Scene Setup ---------------------
        Scene mainScene = new Scene(root, screenWidth, 600);
        stage.setTitle("MEGA STORE - Admin");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}