package com.biblioteca.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Array;

import com.biblioteca.config.DBManager;
// import com.biblioteca.utils.ExceptionHandler;

public class BookDAO {

    // Método para crear un libro
    public void createBook(Book book) {
        String sql = "INSERT INTO books (title, author, description, isbn, gender, pages) VALUES (?,?,?,?,?,?)";
        boolean success = false;

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

            stmn.executeUpdate();
            success = true;
            System.out.println("Libro insertado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al insertar el libro: " + e.getMessage());
        } finally {
            if (success) {
                System.out.println("Operación createBook() finalizada con éxito.");
            } else {
                System.err.println("Operación createBook() finalizada con errores.");
            }
        }
    }

    // Metodo para eliminar un Libro
    public void deleteBook(Book book) {
        String sql = "DELETE FROM books WHERE isbn = ?";
        boolean success = false;

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(sql)) {

            stmn.setLong(1, book.getIsbn());
            stmn.executeUpdate();
            success = true;

            System.out.println("Libro eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el libro: " + e.getMessage());
        } finally {
            if (success) {
                System.out.println("Operación deleteBook() finalizada con éxito.");
            } else {
                System.err.println("Operación deleteBook() finalizada con errores.");
            }
        }
    }

    // Interfaz funcional para preparar declaraciones
    @FunctionalInterface
    private interface StatementPreparer {
        void prepare(PreparedStatement stmn) throws SQLException;
    }
}