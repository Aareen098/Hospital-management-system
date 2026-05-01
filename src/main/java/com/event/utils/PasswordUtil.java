package com.event.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verify(String password, String hashed) {
        if (hashed == null || !hashed.startsWith("$2a$")) {
            throw new RuntimeException("Invalid stored password format!");
        }
        return BCrypt.checkpw(password, hashed);
    }
}