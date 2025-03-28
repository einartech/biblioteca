package com.biblioteca.utils;

import java.sql.SQLException;

public class ExceptionHandler {
    public static void handleSQLException(SQLException e, String message) {
        System.err.println(message + ": " + e.getMessage());
    }
}