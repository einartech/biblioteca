package com.biblioteca.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    
    // Método para buscar un libro por título
    public List<Book> searchBookByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title ILIKE ?";

        try {
            Connection connection = DBManager.initConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, "%" + title + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Convertir los arrays de la base de datos a listas de Strings
                String[] authors = (String[]) rs.getArray("author").getArray();
                String[] genders = (String[]) rs.getArray("gender").getArray();

                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(Arrays.asList(authors));
                book.setDescription(rs.getString("description"));
                book.setIsbn( rs.getLong("isbn"));
                book.setGender(Arrays.asList(genders));
                book.setPages(rs.getInt("pages"));
                book.setYear(rs.getInt("year"));
        
                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar libros por título: " + e.getMessage());
        }

        return books;
    }
}
