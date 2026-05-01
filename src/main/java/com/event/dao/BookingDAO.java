package com.event.dao;

import com.event.model.Booking;
import com.event.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // 🔹 USER BOOK EVENT
    public void bookEvent(int userId, int eventId) {

        String sql = "INSERT INTO bookings(user_id, event_id) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, eventId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 USER: GET THEIR BOOKINGS
    public List<Booking> getUserBookings(int userId) {

        List<Booking> list = new ArrayList<>();

        String sql = """
            SELECT b.booking_id, b.booking_date, e.name
            FROM bookings b
            JOIN events e ON b.event_id = e.event_id
            WHERE b.user_id = ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking b = new Booking();

                b.setBookingId(rs.getInt("booking_id"));
                b.setBookingDate(rs.getTimestamp("booking_date").toLocalDateTime());
                b.setEventName(rs.getString("name"));

                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🔥 ADMIN: GET ALL BOOKINGS (NEW)
    public List<Booking> getAllBookings() {

        List<Booking> list = new ArrayList<>();

        String sql = """
            SELECT b.booking_id, b.booking_date,
                   u.username, e.name
            FROM bookings b
            JOIN users u ON b.user_id = u.user_id
            JOIN events e ON b.event_id = e.event_id
        """;

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Booking b = new Booking();

                b.setBookingId(rs.getInt("booking_id"));
                b.setBookingDate(rs.getTimestamp("booking_date").toLocalDateTime());

                // 🔥 IMPORTANT (for admin UI)
                b.setUsername(rs.getString("username"));
                b.setEventName(rs.getString("name"));

                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🔥 OPTIONAL: CANCEL BOOKING (FUTURE FEATURE)
    public void deleteBooking(int bookingId) {

        String sql = "DELETE FROM bookings WHERE booking_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}