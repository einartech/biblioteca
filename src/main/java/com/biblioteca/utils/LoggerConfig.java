package com.biblioteca.utils;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {
    private static final int LOG_FILE_SIZE = 1024 * 1024; // Tamaño máximo del archivo (1 MB)
    private static final int LOG_FILE_COUNT = 5; // Número máximo de archivos de respaldo

    // Códigos ANSI para colores
    private static final String ANSI_YELLOW = "\u001B[33m"; // Amarillo
    private static final String ANSI_RESET = "\u001B[0m"; // Reset

    /**
     * Configura un logger para una clase específica con un archivo de logs
     * dedicado.
     *
     * @param className   Nombre de la clase que usará el logger.
     * @param logFilePath Ruta del archivo de logs.
     * @return Logger configurado.
     */
    public static Logger getLogger(String className, String logFilePath) {
        Logger logger = Logger.getLogger(className);

        try {
            // Configurar el FileHandler para guardar los logs en un archivo
            FileHandler fileHandler = new FileHandler(logFilePath, LOG_FILE_SIZE, LOG_FILE_COUNT, true);
            fileHandler.setFormatter(new SimpleFormatter()); // Formato simple para los logs
            fileHandler.setLevel(java.util.logging.Level.ALL); // Registrar todos los niveles en el archivo
            logger.addHandler(fileHandler);

            // Configurar el ConsoleHandler para mostrar los logs en la terminal
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter() {
                @Override
                public synchronized String format(java.util.logging.LogRecord record) {
                    return ANSI_YELLOW + super.format(record) + ANSI_RESET; // Mensajes en amarillo
                }
            });
            consoleHandler.setLevel(java.util.logging.Level.ALL);
            logger.addHandler(consoleHandler);

            // Configurar el nivel del Logger
            logger.setLevel(java.util.logging.Level.ALL); // Registrar todos los niveles de logs
        } catch (IOException e) {
            System.err.println("No se pudo configurar el FileHandler para el Logger: " + e.getMessage());
        }

        return logger;
    }
}
