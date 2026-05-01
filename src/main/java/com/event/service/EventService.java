package com.event.service;

import com.event.dao.EventDAO;
import com.event.model.Event;
import com.event.middleware.AuthorizationUtil;

import java.util.List;

public class EventService {

    private EventDAO dao = new EventDAO();

    // 👑 ADMIN ONLY - ADD EVENT
    public void addEvent(Event event) {

        // 🔒 Role check
        if (!AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Only admin can add events!");
        }

        // 🔴 Validation
        validateEvent(event);

        dao.addEvent(event);
    }

    // ✅ BOTH USER + ADMIN
    public List<Event> getAllEvents() {
        return dao.getAllEvents();
    }

    // 👑 ADMIN ONLY - DELETE
    public void deleteEvent(int id) {

        if (!AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Only admin can delete events!");
        }

        if (id <= 0) {
            throw new RuntimeException("Invalid event ID!");
        }

        dao.deleteEvent(id);
    }

    // 👑 ADMIN ONLY - UPDATE (NEW 🔥)
    public void updateEvent(Event event) {

        if (!AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Only admin can update events!");
        }

        if (event.getEventId() <= 0) {
            throw new RuntimeException("Invalid event ID!");
        }

        validateEvent(event);

        dao.updateEvent(event);
    }

    // 🔍 OPTIONAL: Get event by ID (useful later)
    public Event getEventById(int id) {

        if (id <= 0) {
            throw new RuntimeException("Invalid ID!");
        }

        return dao.getAllEvents()
                .stream()
                .filter(e -> e.getEventId() == id)
                .findFirst()
                .orElse(null);
    }

    // 🔥 COMMON VALIDATION METHOD
    private void validateEvent(Event event) {

        if (event == null) {
            throw new RuntimeException("Event cannot be null!");
        }

        if (event.getName() == null || event.getName().isEmpty()) {
            throw new RuntimeException("Event name is required!");
        }

        if (event.getLocation() == null || event.getLocation().isEmpty()) {
            throw new RuntimeException("Event location is required!");
        }

        if (event.getDate() == null) {
            throw new RuntimeException("Event date is required!");
        }

        if (event.getPrice() < 0) {
            throw new RuntimeException("Price cannot be negative!");
        }
    }
}