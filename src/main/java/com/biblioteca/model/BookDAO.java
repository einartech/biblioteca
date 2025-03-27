package com.biblioteca.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Array;

import com.biblioteca.config.DBManager;

public class BookDAO {

    //private PreparedStatement stmn;
    //private Connection connection;

    public void createBook(Book book){

        String sql = "INSERT INTO books (title, author, description, isbn, gender, pages) VALUES (?,?,?,?,?,?)";
        try (Connection connection = DBManager.initConnection();
            PreparedStatement stmn = connection.prepareStatement(sql)) {
            
            
            stmn.setString(1, book.getTitle());
            Array authorArray = connection.createArrayOf("text", book.getAuthor().toArray());
            stmn.setArray(2, authorArray);
            stmn. setString(3, book.getDescription());
            stmn. setLong(4, book. getIsbn());
            Array genderArray = connection.createArrayOf("text", book.getGender().toArray());
            stmn. setArray (5, genderArray) ; stmn. setInt(6, book.getPages ());
            
            stmn. executeUpdate ();
            System.out.println("Libro creado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear el libro: " + e.getMessage());
            }
        }
}

