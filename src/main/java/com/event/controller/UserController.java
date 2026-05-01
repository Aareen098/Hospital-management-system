package com.event.controller;

import com.event.model.User;
import com.event.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.beans.property.*;

public class UserController {

    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, Integer> idCol;
    @FXML private TableColumn<User, String> usernameCol;
    @FXML private TableColumn<User, String> roleCol;

    private UserService service = new UserService();

    @FXML
    public void initialize() {

        idCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getUserId()).asObject());

        usernameCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getUsername()));

        roleCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRole().name()));

        loadUsers();
    }

    private void loadUsers() {
        userTable.setItems(
                FXCollections.observableArrayList(service.getAllUsers())
        );
    }
}