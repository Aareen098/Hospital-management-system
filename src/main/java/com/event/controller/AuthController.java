package com.event.controller;

import com.event.model.User;
import com.event.service.AuthService;
import com.event.utils.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AuthController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    private AuthService authService = new AuthService();

    // 🔐 LOGIN
    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // 🔴 Basic validation
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Please enter username and password!");
            return;
        }

        User user = authService.login(username, password);

        if (user != null) {
            Session.currentUser = user;

            showAlert("Login Successful!");

            // 🔥 ROLE-BASED REDIRECT
            if (user.getRole().name().equals("ADMIN")) {
                loadPage("/com/event/fxml/admin_dashboard.fxml");
            } else {
                loadPage("/com/event/fxml/user_dashboard.fxml");
            }

        } else {
            showAlert("Invalid credentials!");
        }
    }

    // 🔐 REGISTER
    @FXML
    public void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirm = confirmPasswordField.getText();

        // 🔴 Validation
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            showAlert("All fields are required!");
            return;
        }

        if (!password.equals(confirm)) {
            showAlert("Passwords do not match!");
            return;
        }

        boolean success = authService.register(username, password);

        if (success) {
            showAlert("Registration Successful!");
            goToLogin();
        } else {
            showAlert("User already exists!");
        }
    }

    // 🔁 NAVIGATION
    @FXML
    public void goToRegister() {
        loadPage("/com/event/fxml/register.fxml");
    }

    @FXML
    public void goToLogin() {
        loadPage("/com/event/fxml/login.fxml");
    }

    // 🔄 COMMON PAGE LOADER
    private void loadPage(String path) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error loading page!");
        }
    }

    // 🔔 ALERT
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}