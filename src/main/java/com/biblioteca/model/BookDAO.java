package com.biblioteca.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Array;

import com.biblioteca.config.DBManager;

public class BookDAO {

    // Método para crear un libro
    public void createBook(Book book) {
        String sql = "INSERT INTO books (title, author, description, isbn, gender, pages) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(sql)) {

            stmn.setString(1, book.getTitle());
            Array authorArray = connection.createArrayOf("text", book.getAuthor().toArray());
            stmn.setArray(2, authorArray);
            stmn.setString(3, book.getDescription());
            stmn.setLong(4, book.getIsbn());
            Array genderArray = connection.createArrayOf("text", book.getGender().toArray());
            stmn.setArray(5, genderArray);
            stmn.setInt(6, book.getPages());
            stmn.setInt(7, book.getYear());

            stmn.executeUpdate();
            System.out.println("Libro insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el libro: " + e.getMessage());
        }
    }

    // Método para eliminar un libro
    public void deleteBook(long isbn) {
        String sql = "DELETE FROM books WHERE isbn = ?";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(sql)) {

            if (isbn <= 0) {
                System.err.println("El ISBN proporcionado no es válido.");
                return;
            }

            stmn.setLong(1, isbn); // Establecer el ISBN en la consulta
            int rowsAffected = stmn.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No se encontró un libro con el ISBN proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el libro con ISBN " + isbn + ": " + e.getMessage());
        }
    }
    //Modificar un libro
    public void updateBook (Book book) {
       String sql = "UPDATE books SET title = ?, description = ?, isbn = ?, pages = ?, year = ? WHERE book_id = ?";

        try (Connection connection = DBManager.initConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getDescription());
            stmt.setLong(3, book.getIsbn());
            stmt.setInt(4, book.getPages());
            stmt.setInt(5, book.getYear());
            stmt.setInt(6, book.getId());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println(" Libro actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún libro con ese ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}