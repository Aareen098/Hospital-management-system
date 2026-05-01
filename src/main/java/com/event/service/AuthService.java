package com.event.service;

import com.event.dao.UserDAO;
import com.event.model.User;
import com.event.model.Role;
import com.event.utils.PasswordUtil;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    // 🔐 Register
    public boolean register(String username, String password) {

        // check if user already exists
        if (userDAO.findByUsername(username) != null) {
            return false;
        }

        // hash password
        String hashedPassword = PasswordUtil.hash(password);

        User user = new User(username, hashedPassword, Role.USER);

        return userDAO.save(user);
    }

    // 🔐 Login
    public User login(String username, String password) {

        User user = userDAO.findByUsername(username);

        if (user != null && PasswordUtil.verify(password, user.getPassword())) {
            return user;
        }

        return null;
    }
}