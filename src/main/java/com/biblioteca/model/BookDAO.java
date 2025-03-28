package com.biblioteca.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Array;

import com.biblioteca.config.DBManager;
import com.biblioteca.utils.ExceptionHandler;

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

            stmn.executeUpdate();
            System.out.println("Libro insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el libro: " + e.getMessage());
        }
    }

    // Método para eliminar un libro por ID
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        executeUpdate(sql, stmn -> stmn.setInt(1, id));
    }

    // Método para actualizar un libro
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, description = ?, isbn = ?, gender = ?, pages = ? WHERE id = ?";
        executeUpdate(sql, stmn -> {
            stmn.setString(1, book.getTitle());
            Array authorArray = stmn.getConnection().createArrayOf("text", book.getAuthor().toArray());
            stmn.setArray(2, authorArray);
            stmn.setString(3, book.getDescription());
            stmn.setLong(4, book.getIsbn());
            Array genderArray = stmn.getConnection().createArrayOf("text", book.getGender().toArray());
            stmn.setArray(5, genderArray);
            stmn.setInt(6, book.getPages());
            stmn.setInt(7, book.getId());
        });
    }

    // Método genérico para ejecutar consultas de actualización (INSERT, UPDATE,
    // DELETE)
    private void executeUpdate(String sql, StatementPreparer preparer) {
        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(sql)) {

            // Preparar la declaración
            preparer.prepare(stmn);

            // Ejecutar la consulta
            int rowsAffected = stmn.executeUpdate();
            System.out.println("Operación completada. Filas afectadas: " + rowsAffected);

        } catch (SQLException e) {
            ExceptionHandler.handleSQLException(e, "Error al ejecutar la consulta");
        }
    }

    // Interfaz funcional para preparar declaraciones
    @FunctionalInterface
    private interface StatementPreparer {
        void prepare(PreparedStatement stmn) throws SQLException;
    }
}