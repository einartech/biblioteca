package com.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;


public class DBManager {

    //Cargar las variables desde el archivo .env

    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASS = dotenv.get("DB_PASS");

    // ANTES:
    //private static final String URL = "jdbc:postgresql://localhost:5432/pet_adoption";
    // private static final String USER = "postgres";
    // private static final String PASS = "12345";

    //Averiguar como hacer un archivo .env en java

    private static Connection connection;

    public static Connection initConnection(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("¡Conectado con éxito!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("Conexión cerrada correctamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
}
