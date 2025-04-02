package com.biblioteca.controller;

import java.util.List;

import com.biblioteca.model.Book;
import com.biblioteca.model.BookDAO;

public class BookController {
    private final BookDAO bookDAO;

    // Constructor
    public BookController() {
        this.bookDAO = new BookDAO();
    }

    /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param book Objeto Book con los datos del libro.
     */
    public void createBook(Book book) {
        bookDAO.createBook(book);
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     */
    public boolean deleteBook(int id) {
        return bookDAO.deleteBook(id);
    }

    /**
     * Actualiza los datos de un libro existente.
     *
     * @param book Objeto Book con los datos actualizados.
     */
    public boolean updateBook(Book book) {
        return bookDAO.updateBook(book);
    }

    /**
     * Obtiene todos los libros de la base de datos.
     *
     * @return Lista de libros.
     */
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    /**
     * Obtiene libros por género.
     *
     * @param genre Género literario.
     * @return Lista de libros que coinciden con el género.
     */
    public List<Book> getBooksByGenre(String genre) {
        return bookDAO.getBooksByGenre(genre);
    }

    /**
     * Busca libros por título.
     *
     * @param title Título o parte del título del libro.
     * @return Lista de libros que coinciden con el título.
     */
    public List<Book> searchBookByTitle(String title) {
        return bookDAO.searchBookByTitle(title);
    }

    /**
     * Obtiene libros por autor.
     *
     * @param author Nombre del autor.
     * @return Lista de libros del autor.
     */
    public List<Book> getBooksByAuthor(String author) {
        return bookDAO.getBooksByAuthor(author);
    }
}