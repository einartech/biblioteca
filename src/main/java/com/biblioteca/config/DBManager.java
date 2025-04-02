package com.biblioteca.config;

import com.biblioteca.utils.LoggerConfig;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBManager {
    private static final Logger logger = LoggerConfig.getLogger(DBManager.class.getName(), "logs/dbmanager.log");

    // Códigos ANSI para el color amarillo
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Cargar las variables de entorno desde el archivo .env
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASS = dotenv.get("DB_PASS");

    static {
        try {
            // Verificar que las variables de entorno estén configuradas
            validateEnvVariables();

            // Cargar el controlador JDBC
            Class.forName("org.postgresql.Driver");
            logInfo("Controlador JDBC cargado correctamente.");
        } catch (ClassNotFoundException e) {
            logSevere("No se encontró el controlador JDBC: " + e.getMessage());
            throw new RuntimeException("No se encontró el controlador JDBC: " + e.getMessage());
        }
    }

    /**
     * Valida que las variables de entorno necesarias estén configuradas.
     */
    private static void validateEnvVariables() {
        if (URL == null || USER == null || PASS == null) {
            logSevere("Las variables de entorno DB_URL, DB_USER o DB_PASS no están configuradas.");
            throw new IllegalStateException(
                    "Las variables de entorno DB_URL, DB_USER o DB_PASS no están configuradas.");
        }
    }

    /**
     * Inicializa una conexión a la base de datos.
     *
     * @return Una conexión activa a la base de datos.
     */
    public static Connection initConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            logInfo("¡Conexión exitosa a la base de datos!");
            return connection;
        } catch (SQLException e) {
            logSevere("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Cierra una conexión a la base de datos.
     *
     * @param connection La conexión a cerrar.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logInfo("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                logSevere("Error al cerrar la conexión: " + e.getMessage());
            }
        } else {
            logWarning("Intento de cerrar una conexión nula.");
        }
    }

    /**
     * Registra un mensaje de nivel INFO en amarillo.
     *
     * @param message El mensaje a registrar.
     */
    private static void logInfo(String message) {
        logger.info(ANSI_YELLOW + message + ANSI_RESET);
    }

    /**
     * Registra un mensaje de nivel WARNING en amarillo.
     *
     * @param message El mensaje a registrar.
     */
    private static void logWarning(String message) {
        logger.warning(ANSI_YELLOW + message + ANSI_RESET);
    }

    /**
     * Registra un mensaje de nivel SEVERE en amarillo.
     *
     * @param message El mensaje a registrar.
     */
    private static void logSevere(String message) {
        logger.severe(ANSI_YELLOW + message + ANSI_RESET);
    }
}
