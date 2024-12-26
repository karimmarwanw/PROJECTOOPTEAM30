package com.example.finalproject;

import Entity.Database;
import Entity.Customer;
import Entity.Admin;
import Entity.Supplier;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController extends Application {

    private TextField usernameField;
    private PasswordField passwordField;
    private Label loginFeedbackLabel;
    private Hyperlink signUpLink;
    @Override
    public void start(Stage primaryStage) {

        Database.create();
        primaryStage.setTitle("Login");

        // ------------------- Banner Image ---------------------
        Image bannerImage = new Image("file:D:\\Uni\\OOP\\FINALPROJECT\\src\\theme\\360_F_482004517_6duaJUqQeaJFtgWSIeT2D4kcyixmauoE.jpg");
        ImageView bannerImageView = new ImageView(bannerImage);
        bannerImageView.setFitWidth(800);
        bannerImageView.setFitHeight(150);
        bannerImageView.setPreserveRatio(false);

        // ------------------- Center Image ---------------------
        Image centerImage = new Image("file:D:\\Uni\\OOP\\FINALPROJECT\\src\\theme\\mega no bg.png");
        ImageView centerImageView = new ImageView(centerImage);
        centerImageView.setFitWidth(150);
        centerImageView.setFitHeight(150);
        centerImageView.setPreserveRatio(true);

        StackPane bannerPane = new StackPane();
        bannerPane.getChildren().addAll(bannerImageView, centerImageView);
        StackPane.setAlignment(bannerImageView, Pos.TOP_CENTER);

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        loginFeedbackLabel = new Label();

        signUpLink = new Hyperlink("Sign Up");
        signUpLink.setOnAction(event -> handleSignUpLink(primaryStage));

        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> handleLogin(primaryStage));

        VBox vbox = new VBox(10, usernameField, passwordField, loginButton, loginFeedbackLabel, signUpLink);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(bannerPane, vbox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleLogin(Stage primaryStage) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        new Thread(() -> {
            boolean isAdmin = false;
            boolean isCustomer = false;

            for (Admin admin : Database.admins) {
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    isAdmin = true;
                    break;
                }
            }

            if (!isAdmin) {
                for (Customer customer : Database.customers) {
                    if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                        isCustomer = true;
                        break;
                    }
                }
            }

            boolean finalIsAdmin = isAdmin;
            boolean finalIsCustomer = isCustomer;
            Platform.runLater(() -> {
                if (finalIsAdmin) {
                    loginFeedbackLabel.setText("Login successful.");
                    AdminScreen adminScreen = new AdminScreen();
                    try {
                        adminScreen.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (finalIsCustomer) {
                    loginFeedbackLabel.setText("Login successful.");
                    FinalProject customerScreen = new FinalProject();
                    try {
                        customerScreen.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    loginFeedbackLabel.setText("Invalid username or password.");
                }
            });
        }).start();
    }

    public boolean login(String username, String password) {
        for (Admin admin : Database.admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }

        for (Customer customer : Database.customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return false;
            }
        }

        for (Supplier supplier : Database.suppliers) {
            if (supplier.getUsername().equals(username) && supplier.getPassword().equals(password)) {
                return false;
            }
        }

        return false;
    }

    public void handleSignUpLink(Stage primaryStage) {
        SignUpController signUpController = new SignUpController();
        try {
            signUpController.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}