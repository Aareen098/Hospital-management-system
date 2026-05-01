package com.event;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        // 🔥 Load initial screen
        switchScene("/com/event/fxml/login.fxml", "Login");

        primaryStage.setWidth(500);
        primaryStage.setHeight(400);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    // 🔄 GLOBAL SCENE SWITCHER (VERY IMPORTANT)
    public static void switchScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            primaryStage.setTitle(title);
            primaryStage.setScene(scene);

        } catch (Exception e) {
            System.out.println("❌ Failed to load: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}