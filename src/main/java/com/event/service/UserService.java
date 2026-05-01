package com.event.service;

import com.event.dao.UserDAO;
import com.event.model.User;
import com.event.model.Role;
import com.event.middleware.AuthorizationUtil;

import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // 👑 ADMIN ONLY - GET ALL USERS
    public List<User> getAllUsers() {

        if (!AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Only admin can view users!");
        }

        return userDAO.getAllUsers();
    }

    // 👑 ADMIN ONLY - DELETE USER (optional)
    public void deleteUser(int userId) {

        if (!AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Only admin can delete users!");
        }

        if (userId <= 0) {
            throw new RuntimeException("Invalid user ID!");
        }

        userDAO.deleteUser(userId);
    }

    // 👑 ADMIN ONLY - CHANGE ROLE (optional 🔥)
    public void updateUserRole(int userId, Role role) {

        if (!AuthorizationUtil.isAdmin()) {
            throw new RuntimeException("Only admin can update roles!");
        }

        if (userId <= 0) {
            throw new RuntimeException("Invalid user ID!");
        }

        userDAO.updateUserRole(userId, role);
    }
}