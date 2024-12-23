package com.example.finalproject;

import Entity.*;
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

import java.util.ArrayList;

public class SignUpController extends Application {

    private TextField usernameField;
    private PasswordField passwordField;
    private TextField dateOfBirthField;
    private MenuButton roleMenuButton;
    private Label signUpFeedbackLabel;
    private Hyperlink backToLoginLink;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up");

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

        dateOfBirthField = new TextField();
        dateOfBirthField.setPromptText("Date of Birth");

        roleMenuButton = new MenuButton("Select Role");
        MenuItem adminMenuItem = new MenuItem("Admin (CEO)");
        MenuItem customerMenuItem = new MenuItem("Customer");
        MenuItem supplierMenuItem = new MenuItem("Supplier");
        roleMenuButton.getItems().addAll(adminMenuItem, customerMenuItem, supplierMenuItem);

        adminMenuItem.setOnAction(event -> roleMenuButton.setText(adminMenuItem.getText()));
        customerMenuItem.setOnAction(event -> roleMenuButton.setText(customerMenuItem.getText()));
        supplierMenuItem.setOnAction(event -> roleMenuButton.setText(supplierMenuItem.getText()));

        signUpFeedbackLabel = new Label();

        backToLoginLink = new Hyperlink("Back to Login");
        backToLoginLink.setOnAction(event -> handleBackToLogin(primaryStage));

        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(event -> handleSignUp());

        VBox vbox = new VBox(10, usernameField, passwordField, dateOfBirthField, roleMenuButton, signUpButton, signUpFeedbackLabel, backToLoginLink);
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

    public void handleSignUp() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String dateOfBirth = dateOfBirthField.getText();
        String role = roleMenuButton.getText();

        new Thread(() -> {
            boolean success = signUp(username, password, dateOfBirth, role);
            Platform.runLater(() -> {
                if (success) {
                    signUpFeedbackLabel.setText("Sign up successful. You can now log in.");
                } else {
                    signUpFeedbackLabel.setText("Sign up failed. Username may already be taken or invalid date format.");
                }
            });
        }).start();
    }

    public boolean signUp(String username, String password, String dateOfBirth, String role) {
        if (!dateOfBirth.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }

        // Check if username already exists
        for (Admin admin : Database.admins) {
            if (admin.getUsername().equals(username)) {
                return false;
            }
        }
        for (Customer customer : Database.customers) {
            if (customer.getUsername().equals(username)) {
                return false;
            }
        }
        for (Supplier supplier : Database.suppliers) {
            if (supplier.getUsername().equals(username)) {
                return false;
            }
        }

        switch (role) {
            case "Admin (CEO)":
                Admin newAdmin = new Admin(username, password, dateOfBirth, "ceo", 0);
                Database.admins.add(newAdmin);
                break;
            case "Customer":
                Customer newCustomer = new Customer(username, password, dateOfBirth, 10000.0, "address", Gender.MALE, new ArrayList<>(), new Cart());
                Database.customers.add(newCustomer);
                break;
            case "Supplier":
                Supplier newSupplier = new Supplier(1, username, password, dateOfBirth, "contact");
                Database.suppliers.add(newSupplier);
                break;
            default:
                return false;
        }
        return true;
    }

    public void handleBackToLogin(Stage primaryStage) {
        LoginController loginController = new LoginController();
        try {
            loginController.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}