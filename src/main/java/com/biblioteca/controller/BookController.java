package com.biblioteca.controller;

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
}
