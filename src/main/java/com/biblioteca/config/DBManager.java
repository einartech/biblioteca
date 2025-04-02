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

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el controlador JDBC: " + e.getMessage());
        }
    }

    private static void validateEnvVariables() {
        if (URL == null || USER == null || PASS == null) {
            throw new IllegalStateException(
                    "Las variables de entorno DB_URL, DB_USER o DB_PASS no están configuradas.");
        }
    }

    public static Connection initConnection() {
        validateEnvVariables();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            logger.info("¡Conexión exitosa a la base de datos!");
            return connection;
        } catch (SQLException e) {
            logger.severe("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                logger.severe("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
