package com.event.controller;

import com.event.model.Booking;
import com.event.service.BookingService;
import com.event.utils.Session;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.beans.property.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MyBookingController {

    @FXML private TableView<Booking> bookingTable;

    @FXML private TableColumn<Booking, Integer> idCol;
    @FXML private TableColumn<Booking, String> eventCol;
    @FXML private TableColumn<Booking, String> dateCol;

    private BookingService bookingService = new BookingService();

    @FXML
    public void initialize() {

        // Table bindings
        idCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getBookingId()).asObject());

        eventCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEventName()));

        dateCol.setCellValueFactory(data -> {
            String formattedDate = data.getValue().getBookingDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new SimpleStringProperty(formattedDate);
        });

        loadBookings();
    }

    private void loadBookings() {
        int userId = Session.currentUser.getUserId();

        List<Booking> list = bookingService.getUserBookings(userId);
        bookingTable.setItems(FXCollections.observableArrayList(list));
    }
}