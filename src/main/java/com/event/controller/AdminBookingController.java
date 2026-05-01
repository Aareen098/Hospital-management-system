package com.event.controller;

import com.event.model.Booking;
import com.event.service.BookingService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.beans.property.*;

import java.time.format.DateTimeFormatter;

public class AdminBookingController {

    @FXML private TableView<Booking> bookingTable;
    @FXML private TableColumn<Booking, Integer> idCol;
    @FXML private TableColumn<Booking, String> userCol;
    @FXML private TableColumn<Booking, String> eventCol;
    @FXML private TableColumn<Booking, String> dateCol;

    private BookingService service = new BookingService();

    @FXML
    public void initialize() {

        idCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getBookingId()).asObject());

        userCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getUsername()));

        eventCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEventName()));

        dateCol.setCellValueFactory(data -> {
            String date = data.getValue().getBookingDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new SimpleStringProperty(date);
        });

        loadBookings();
    }

    private void loadBookings() {
        var list = service.getAllBookings();
        System.out.println("Bookings: " + list.size()); // debug
        bookingTable.setItems(FXCollections.observableArrayList(list));
    }
}