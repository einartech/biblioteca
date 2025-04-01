package com.biblioteca.model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            // stmn.setInt(7, book.getYear());

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

    // Modificar un libro
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, description = ?, isbn = ?, pages = ? WHERE id = ?";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getDescription());
            stmt.setLong(3, book.getIsbn());
            stmt.setInt(4, book.getPages());
            stmt.setInt(5, book.getId()); // Asegúrate de que el ID del libro esté configurado correctamente

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("El libro ha sido actualizado correctamente.");
            } else {
                System.out.println("No se encontró un libro con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el libro: " + e.getMessage());
        }
    }

    // Metodo para ver todos los libros
    public void getAllBooks() {
        String sql = "SELECT * FROM books";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(sql)) {

            // Aquí puedes ejecutar la consulta y procesar los resultados
            ResultSet rs = stmn.executeQuery();
            while (rs.next()) {
                System.out.println("---------------------------");
                System.out.println("Título: " + rs.getString("title"));
                System.out.println("Autor(es): " + rs.getArray("author"));
                System.out.println("Descripción: " + rs.getString("description"));
                System.out.println("ISBN: " + rs.getLong("isbn"));
                System.out.println("Género(s): " + rs.getArray("gender"));
                System.out.println("Páginas: " + rs.getInt("pages"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los libros: " + e.getMessage());
        }
    }

    // Metodo para filtrar los libros por su género
    public void getBooksByGenre(String genre) {
        String sql = "SELECT id, title, author, description, isbn, pages FROM books WHERE ? = ANY (gender)";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, genre); // Establecer el género en la consulta
            ResultSet rs = stmt.executeQuery();

            System.out.println("Libros encontrados para el género '" + genre + "':");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String description = rs.getString("description");
                long isbn = rs.getLong("isbn");
                int pages = rs.getInt("pages");

                System.out.println("ID: " + id);
                System.out.println("Título: " + title);
                System.out.println("Autor(es): " + author);
                System.out.println("Descripción: " + description);
                System.out.println("ISBN: " + isbn);
                System.out.println("Páginas: " + pages);
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los libros: " + e.getMessage());
        }
    }

    // Método para buscar libros por autor
    public List<Book> getBookByAuthor(List<String> authors) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author && ?::text[]";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(sql)) {

            Array authorArray = connection.createArrayOf("text", authors.toArray());
            stmn.setArray(1, authorArray);

            ResultSet rs = stmn.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        Arrays.asList((String[]) rs.getArray("author").getArray()),
                        rs.getString("description"),
                        rs.getLong("isbn"),
                        Arrays.asList((String[]) rs.getArray("gender").getArray()),
                        rs.getInt("pages"));
                books.add(book);
            }

            System.out.println("Libros encontrados mediante autor correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al buscar libros por autor: " + e.getMessage());
        }

        return books;
    }

}