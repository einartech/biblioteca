package com.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import io.github.cdimascio.dotenv.Dotenv;

public class DBManager {
    private static final Logger logger = Logger.getLogger(DBManager.class.getName());
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASS = dotenv.get("DB_PASS");

    // Códigos ANSI para colores
    private static final String ANSI_YELLOW = "\u001B[33m"; // Amarillo para INFO
    private static final String ANSI_RED = "\u001B[31m"; // Rojo para SEVERE
    private static final String ANSI_RESET = "\u001B[0m"; // Reset para volver al color predeterminado

    static {
        try {
            Class.forName("org.postgresql.Driver");
            logInfo("Controlador JDBC cargado correctamente.");
        } catch (ClassNotFoundException e) {
            logSevere("No se encontró el controlador JDBC: " + e.getMessage());
            throw new RuntimeException("No se encontró el controlador JDBC: " + e.getMessage());
        }
    }

    private static void validateEnvVariables() {
        if (URL == null || USER == null || PASS == null) {
            logSevere("Las variables de entorno DB_URL, DB_USER o DB_PASS no están configuradas.");
            throw new IllegalStateException(
                    "Las variables de entorno DB_URL, DB_USER o DB_PASS no están configuradas.");
        }
    }

    public static Connection initConnection() {
        validateEnvVariables();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            logInfo("¡Conexión exitosa a la base de datos!");
            return connection;
        } catch (SQLException e) {
            logSevere("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logInfo("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                logSevere("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    // Métodos auxiliares para imprimir mensajes con colores
    private static void logInfo(String message) {
        logger.info(ANSI_YELLOW + message + ANSI_RESET);
    }

    private static void logSevere(String message) {
        logger.severe(ANSI_RED + message + ANSI_RESET);
    }
}
