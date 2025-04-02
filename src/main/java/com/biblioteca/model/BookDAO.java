package com.biblioteca.model;

import java.sql.Array;
import java.sql.Connection;
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
        String sql = "INSERT INTO books (title, author, description, isbn, genre, pages, publisher, year) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(sql)) {

            stmn.setString(1, book.getTitle());
            Array authorArray = connection.createArrayOf("text", book.getAuthor().toArray());
            stmn.setArray(2, authorArray);
            stmn.setString(3, book.getDescription());
            stmn.setLong(4, book.getIsbn());
            Array genreArray = connection.createArrayOf("text", book.getGenre().toArray());
            stmn.setArray(5, genreArray);
            stmn.setInt(6, book.getPages());
            stmn.setString(7, book.getPublisher());
            stmn.setInt(8, book.getYear());

            stmn.executeUpdate();
            System.out.println("Libro insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el libro: " + e.getMessage());
        }
    }

    // Método para eliminar un libro
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(sql)) {

            if (id <= 0) {
                System.err.println("El ISBN proporcionado no es válido.");
                return;
            }

            stmn.setLong(1, id); // Establecer el ISBN en la consulta
            int rowsAffected = stmn.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No se encontró un libro con el id proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el libro con id " + id + ": " + e.getMessage());
        }
    }

    // Modificar un libro
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, description = ?, isbn = ?, genre = ?, pages = ?, publisher = ?, year = ? WHERE id = ?";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());

            // Manejar autores nulos
            if (book.getAuthor() != null) {
                Array authorArray = connection.createArrayOf("text", book.getAuthor().toArray());
                stmt.setArray(2, authorArray);
            } else {
                stmt.setNull(2, java.sql.Types.ARRAY);
            }

            stmt.setString(3, book.getDescription());
            stmt.setLong(4, book.getIsbn());

            // Manejar géneros nulos
            if (book.getGenre() != null) {
                Array genreArray = connection.createArrayOf("text", book.getGenre().toArray());
                stmt.setArray(5, genreArray);
            } else {
                stmt.setNull(5, java.sql.Types.ARRAY);
            }

            stmt.setInt(6, book.getPages());
            stmt.setString(7, book.getPublisher());
            stmt.setInt(8, book.getYear());
            stmt.setInt(9, book.getId());

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
                System.out.println("Descripcion: " + rs.getString("description"));
                System.out.println("ISBN: " + rs.getLong("isbn"));
                System.out.println("Género(s): " + rs.getArray("genre"));
                System.out.println("Páginas: " + rs.getInt("pages"));
                System.out.println("Editorial: " + rs.getString("publisher"));
                System.out.println("Año : " + rs.getInt("year"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los libros: " + e.getMessage());
        }
    }

    // Metodo para filtrar los libros por su género
    public void getBooksByGenre(String genre) {
        String sql = "SELECT id, title, author, isbn, pages, publisher, year FROM books WHERE ? = ANY (genre)";

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, genre); // Establecer el género en la consulta
            ResultSet rs = stmt.executeQuery();

            System.out.println("Libros encontrados para el género '" + genre + "':");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                long isbn = rs.getLong("isbn");
                int pages = rs.getInt("pages");
                String publisher = rs.getString("publisher");
                int year = rs.getInt("year");

                System.out.println("ID: " + id);
                System.out.println("Título: " + title);
                System.out.println("Autor(es): " + author);
                System.out.println("ISBN: " + isbn);
                System.out.println("Páginas: " + pages);
                System.out.println("Editorial: " + publisher);
                System.out.println("Year: " + year);
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los libros: " + e.getMessage());
        }
    }

    // Método para buscar libros por autor
    public List<Book> getBookByAuthor(List<String> authors) {
        String sql = "SELECT id, title, isbn, genre, pages, publisher, year FROM books WHERE author && ?";

        List<Book> books = new ArrayList<>();

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Convertir la lista de autores a un arreglo de PostgreSQL
            Array authorArray = connection.createArrayOf("text", authors.toArray());
            stmt.setArray(1, authorArray); // Establecer el arreglo en la consulta

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setIsbn(rs.getLong("isbn"));
                book.setGenre(Arrays.asList((String[]) rs.getArray("genre").getArray()));
                book.setPages(rs.getInt("pages"));
                book.setPublisher(rs.getString("publisher"));
                book.setYear(rs.getInt("year"));

                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los libros: " + e.getMessage());
        }

        return books;
    }

    // Método para buscar libros por título
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
                String[] genres = (String[]) rs.getArray("genre").getArray();

                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(Arrays.asList(authors));
                book.setDescription(rs.getString("description"));
                book.setIsbn(rs.getLong("isbn"));
                book.setGenre(Arrays.asList(genres)); // Ahora funcionará
                book.setPages(rs.getInt("pages"));
                book.setPublisher(rs.getString("publisher"));
                book.setYear(rs.getInt("year"));

                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar libros por título: " + e.getMessage());
        }

        return books;
    }
}