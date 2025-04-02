package com.biblioteca.model;

import com.biblioteca.config.DBManager;
import com.biblioteca.utils.LoggerConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class BookDAO {
    // Configurar el logger para esta clase con un archivo de logs específico
    private static final Logger logger = LoggerConfig.getLogger(BookDAO.class.getName(), "logs/bookdao.log");

    // Consultas SQL como constantes
    private static final String SQL_INSERT_BOOK = "INSERT INTO books (title, author, description, isbn, genre, pages, publisher, year) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_BOOK = "DELETE FROM books WHERE id = ?";
    private static final String SQL_UPDATE_BOOK = "UPDATE books SET title = ?, author = ?, description = ?, isbn = ?, genre = ?, pages, publisher, year = ? WHERE id = ?";
    private static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM books";
    private static final String SQL_SELECT_BOOKS_BY_GENRE = "SELECT * FROM books WHERE ? = ANY (genre)";
    private static final String SQL_SEARCH_BOOK_BY_TITLE = "SELECT * FROM books WHERE LOWER(title) LIKE LOWER(?)";
    private static final String SQL_SEARCH_BOOK_BY_AUTHOR = "SELECT * FROM books WHERE ? = ANY (author)";

    /**
     * Método para crear un libro en la base de datos.
     *
     * @param book Objeto Book con los datos del libro a insertar.
     */
    public void createBook(Book book) {
        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(SQL_INSERT_BOOK)) {

            setBookParameters(stmn, book);
            stmn.executeUpdate();
            logger.info("Libro insertado correctamente: " + book.getTitle());
        } catch (SQLException e) {
            logger.severe("Error al insertar el libro: " + e.getMessage());
        }
    }

    /**
     * Método para eliminar un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     * @return true si el libro fue eliminado, false en caso contrario.
     */
    public boolean deleteBook(int id) {
        if (id <= 0) {
            logger.warning("El id proporcionado no es válido.");
            return false;
        }

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(SQL_DELETE_BOOK)) {

            stmn.setInt(1, id);
            int rowsAffected = stmn.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Libro eliminado correctamente con ID: " + id);
                return true;
            } else {
                logger.warning("No se encontró un libro con el id proporcionado: " + id);
                return false;
            }
        } catch (SQLException e) {
            logger.severe("Error al eliminar el libro con id " + id + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para actualizar un libro en la base de datos.
     *
     * @param book Objeto Book con los datos actualizados.
     * @return true si el libro fue actualizado, false en caso contrario.
     */
    public boolean updateBook(Book book) {
        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_BOOK)) {

            setBookParameters(stmt, book);
            stmt.setInt(9, book.getId()); // ID del libro
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("El libro ha sido actualizado correctamente: " + book.getTitle());
                return true;
            } else {
                logger.warning("No se encontró un libro con el ID proporcionado: " + book.getId());
                return false;
            }
        } catch (SQLException e) {
            logger.severe("Error al actualizar el libro: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para obtener todos los libros de la base de datos.
     *
     * @return Lista de objetos Book.
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmn = connection.prepareStatement(SQL_SELECT_ALL_BOOKS);
                ResultSet rs = stmn.executeQuery()) {

            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
            logger.info("Se obtuvieron " + books.size() + " libros de la base de datos.");
        } catch (SQLException e) {
            logger.severe("Error al obtener los libros: " + e.getMessage());
        }

        return books;
    }

    /**
     * Método para buscar libros por género.
     *
     * @param genre Género literario a buscar.
     * @return Lista de objetos Book que coinciden con el género.
     */
    public List<Book> getBooksByGenre(String genre) {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BOOKS_BY_GENRE)) {

            stmt.setString(1, genre);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(mapResultSetToBook(rs));
                }
            }
            logger.info("Se obtuvieron " + books.size() + " libros del género: " + genre);
        } catch (SQLException e) {
            logger.severe("Error al obtener los libros por género: " + e.getMessage());
        }

        return books;
    }

    /**
     * Busca libros por título.
     *
     * @param title Título o parte del título del libro.
     * @return Lista de libros que coinciden con el título.
     */
    public List<Book> searchBookByTitle(String title) {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL_SEARCH_BOOK_BY_TITLE)) {

            stmt.setString(1, "%" + title + "%"); // Usar comodines para búsqueda parcial
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(mapResultSetToBook(rs));
                }
            }
            logger.info("Se encontraron " + books.size() + " libros con el título: " + title);
        } catch (SQLException e) {
            logger.severe("Error al buscar libros por título: " + e.getMessage());
        }

        return books;
    }

    /**
     * Busca libros por autor.
     *
     * @param author Nombre del autor.
     * @return Lista de libros del autor.
     */
    public List<Book> getBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DBManager.initConnection();
                PreparedStatement stmt = connection.prepareStatement(SQL_SEARCH_BOOK_BY_AUTHOR)) {

            stmt.setString(1, author);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(mapResultSetToBook(rs));
                }
            }
            logger.info("Se encontraron " + books.size() + " libros del autor: " + author);
        } catch (SQLException e) {
            logger.severe("Error al buscar libros por autor: " + e.getMessage());
        }

        return books;
    }

    // Métodos auxiliares

    /**
     * Convierte un ResultSet en un objeto Book.
     *
     * @param rs ResultSet con los datos del libro.
     * @return Objeto Book.
     * @throws SQLException Si ocurre un error al leer el ResultSet.
     */
    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                Arrays.asList((String[]) rs.getArray("author").getArray()),
                rs.getString("description"),
                rs.getLong("isbn"),
                Arrays.asList((String[]) rs.getArray("genre").getArray()),
                rs.getInt("pages"),
                rs.getInt("year"),
                rs.getString("publisher"));
    }

    /**
     * Crea un arreglo SQL a partir de una lista de cadenas.
     *
     * @param connection Conexión a la base de datos.
     * @param list       Lista de cadenas.
     * @return Objeto Array para usar en consultas SQL.
     * @throws SQLException Si ocurre un error al crear el arreglo.
     */
    private Array createSqlArray(Connection connection, List<String> list) throws SQLException {
        return list != null ? connection.createArrayOf("text", list.toArray()) : null;
    }

    /**
     * Establece los parámetros de un PreparedStatement para un objeto Book.
     *
     * @param stmt PreparedStatement.
     * @param book Objeto Book.
     * @throws SQLException Si ocurre un error al establecer los parámetros.
     */
    private void setBookParameters(PreparedStatement stmt, Book book) throws SQLException {
        stmt.setString(1, book.getTitle());
        stmt.setArray(2, createSqlArray(stmt.getConnection(), book.getAuthor()));
        stmt.setString(3, book.getDescription());
        stmt.setLong(4, book.getIsbn());
        stmt.setArray(5, createSqlArray(stmt.getConnection(), book.getGenre()));
        stmt.setInt(6, book.getPages());
        stmt.setString(7, book.getPublisher());
        stmt.setInt(8, book.getYear());
    }
}