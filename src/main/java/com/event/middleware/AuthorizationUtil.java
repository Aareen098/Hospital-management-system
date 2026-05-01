package com.event.middleware;

import com.event.utils.Session;

public class AuthorizationUtil {

    public static boolean isAdmin() {
        return Session.currentUser != null &&
                Session.currentUser.getRole().name().equals("ADMIN");
    }
}