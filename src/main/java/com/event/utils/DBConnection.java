package com.event.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // 🔐 DB Configuration
    private static final String URL = "jdbc:mysql://localhost:3306/event_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "9646348062"; // 🔴 change this

    private static Connection connection;

    // 🔹 Get Connection (Singleton pattern)
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database Connected");
            }
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed");
            e.printStackTrace();
        }
        return connection;
    }

    // 🔹 Close Connection (optional)
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Database Disconnected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}