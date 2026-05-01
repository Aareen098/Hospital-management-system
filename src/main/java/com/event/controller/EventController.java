package com.event.controller;

import com.event.model.Event;
import com.event.service.EventService;
import com.event.service.BookingService;
import com.event.middleware.AuthorizationUtil;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.beans.property.*;

import java.time.LocalDate;

public class EventController {

    @FXML private TableView<Event> eventTable;
    @FXML private TableColumn<Event, Integer> idCol;
    @FXML private TableColumn<Event, String> nameCol;
    @FXML private TableColumn<Event, String> locationCol;
    @FXML private TableColumn<Event, String> dateCol;
    @FXML private TableColumn<Event, Double> priceCol;

    @FXML private TextField nameField;
    @FXML private TextField locationField;
    @FXML private TextField priceField;
    @FXML private DatePicker datePicker; // 🔥 NEW

    @FXML private Button addBtn;
    @FXML private Button deleteBtn;
    @FXML private Button bookBtn;

    private EventService eventService = new EventService();
    private BookingService bookingService = new BookingService();

    @FXML
    public void initialize() {

        // ✅ Bind table columns
        idCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getEventId()).asObject());

        nameCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        locationCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getLocation()));

        dateCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDate().toString())); // 🔥 DATE

        priceCol.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getPrice()).asObject());

        // 🔒 Role-based UI
        if (AuthorizationUtil.isAdmin()) {
            bookBtn.setVisible(false);
        } else {
            addBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }

        loadEvents();
    }

    private void loadEvents() {
        ObservableList<Event> list =
                FXCollections.observableArrayList(eventService.getAllEvents());
        eventTable.setItems(list);
    }

    // 👑 ADMIN ONLY
    @FXML
    public void handleAdd() {

        try {
            // 🔴 Validation
            if (nameField.getText().isEmpty() ||
                    locationField.getText().isEmpty() ||
                    priceField.getText().isEmpty() ||
                    datePicker.getValue() == null) {

                showAlert("All fields are required!");
                return;
            }

            Event e = new Event();
            e.setName(nameField.getText());
            e.setLocation(locationField.getText());
            e.setPrice(Double.parseDouble(priceField.getText()));

            // 🔥 IMPORTANT FIX
            e.setDate(datePicker.getValue());

            eventService.addEvent(e);

            showAlert("Event Added Successfully!");
            clearFields();
            loadEvents();

        } catch (NumberFormatException ex) {
            showAlert("Price must be a valid number!");
        } catch (Exception ex) {
            showAlert("Error adding event!");
            ex.printStackTrace();
        }
    }

    // 👑 ADMIN ONLY
    @FXML
    public void handleDelete() {

        Event selected = eventTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Select an event to delete!");
            return;
        }

        eventService.deleteEvent(selected.getEventId());

        showAlert("Event Deleted!");
        loadEvents();
    }

    // 👤 USER ONLY
    @FXML
    public void handleBook() {

        Event selected = eventTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Select an event to book!");
            return;
        }

        bookingService.bookEvent(selected.getEventId());

        showAlert("Event Booked Successfully!");
    }

    // 🔄 Clear form
    private void clearFields() {
        nameField.clear();
        locationField.clear();
        priceField.clear();
        datePicker.setValue(null);
    }

    // 🔔 Alert helper
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}