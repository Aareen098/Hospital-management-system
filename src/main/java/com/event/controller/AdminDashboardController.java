package com.event.controller;

import com.event.middleware.AuthorizationUtil;
import com.event.utils.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class AdminDashboardController {

    @FXML private StackPane contentArea;

    @FXML
    public void loadEvents() {
        loadPage("/com/event/fxml/event.fxml");
    }

    @FXML
    public void loadUsers() {
        if (!AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Unauthorized access");
        }
        loadPage("/com/event/fxml/user.fxml");
    }

    @FXML
    public void loadBookings() {
        if (!AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Unauthorized access");
        }
        loadPage("/com/event/fxml/admin_booking.fxml"); // ✅ FIXED
    }

    @FXML
    public void logout() {
        Session.currentUser = null;
        switchScene("/com/event/fxml/login.fxml");
    }

    private void loadPage(String path) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            contentArea.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchScene(String path) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) contentArea.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}