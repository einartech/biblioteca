package com.biblioteca.controller;

import java.util.Collections;

import com.biblioteca.model.Book;
import com.biblioteca.model.BookDAO;

public class BookController {
    private final BookDAO bookDAO;

    // Constructor sin parámetros
    public BookController() {
        this.bookDAO = new BookDAO(); // Inicializa BookDAO
    }

    // Método para crear un libro
    public void createBook(Book book) {
        bookDAO.createBook(book);
    }

    // Método para eliminar un libro por ISBN
    public void deleteBook(long isbn) {
        bookDAO.deleteBook(isbn);
    }

    // Método para ver todos los libros
    public void getAllBooks() {
        bookDAO.getAllBooks();
    }

    // Método para ver un libro por autor
    public void getBookByAuthor(String author) {
        bookDAO.getBookByAuthor(Collections.singletonList(author));
    }
}
