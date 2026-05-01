package com.event.service;

import com.event.dao.BookingDAO;
import com.event.middleware.AuthorizationUtil;
import com.event.utils.Session;

public class BookingService {

    private BookingDAO dao = new BookingDAO();

    // 👤 USER: BOOK EVENT
    public void bookEvent(int eventId) {

        // 🔒 Block admin
        if (AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Admin cannot book events!");
        }

        // 🔴 Check login
        if (Session.currentUser == null) {
            throw new RuntimeException("User not logged in!");
        }

        int userId = Session.currentUser.getUserId();

        // 🔥 Call DAO
        dao.bookEvent(userId, eventId);
    }

    // 👤 USER: GET THEIR BOOKINGS
    public java.util.List<com.event.model.Booking> getUserBookings(int userId) {
        return dao.getUserBookings(userId);
    }

    // 👑 ADMIN: GET ALL BOOKINGS
    public java.util.List<com.event.model.Booking> getAllBookings() {

        if (!AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Only admin can view all bookings!");
        }

        return dao.getAllBookings();
    }
}