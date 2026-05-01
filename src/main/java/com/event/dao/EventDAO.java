package com.event.dao;

import com.event.model.Event;
import com.event.utils.DBConnection;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class EventDAO {

    // ✅ ADD EVENT (FIXED)
    public void addEvent(Event e) {
        String sql = "INSERT INTO events(name, location, event_date, price) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getName());
            ps.setString(2, e.getLocation());

            // 🔥 IMPORTANT FIX
            ps.setDate(3, Date.valueOf(e.getDate()));

            ps.setDouble(4, e.getPrice());

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ✅ GET ALL EVENTS
    public List<Event> getAllEvents() {
        List<Event> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT * FROM events");

            while (rs.next()) {
                Event e = new Event();

                e.setEventId(rs.getInt("event_id"));
                e.setName(rs.getString("name"));
                e.setLocation(rs.getString("location"));

                // 🔥 IMPORTANT FIX
                e.setDate(rs.getDate("event_date").toLocalDate());

                e.setPrice(rs.getDouble("price"));

                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ DELETE EVENT
    public void deleteEvent(int id) {
        String sql = "DELETE FROM events WHERE event_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔥 BONUS: UPDATE EVENT (HIGHLY RECOMMENDED)
    public void updateEvent(Event e) {
        String sql = "UPDATE events SET name=?, location=?, event_date=?, price=? WHERE event_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getName());
            ps.setString(2, e.getLocation());
            ps.setDate(3, Date.valueOf(e.getDate()));
            ps.setDouble(4, e.getPrice());
            ps.setInt(5, e.getEventId());

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}