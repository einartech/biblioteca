package com.biblioteca.controller;

import java.util.Collections;
import java.util.List;

import com.biblioteca.model.Book;
import com.biblioteca.model.BookDAO;

public class BookController {
    private final BookDAO bookDAO;

    public BookController() {
        this.bookDAO = new BookDAO();
    }

    public void createBook(Book book) {
        bookDAO.createBook(book);
    }

    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    public void getAllBooks() {
        bookDAO.getAllBooks();
    }

    public void getBooksByGenre(String genre) {
        bookDAO.getBooksByGenre(genre);
    }

    public List<Book> searchBookByTitle(String title) {
        return bookDAO.searchBookByTitle(title);
    }

    public void getBookByAuthor(String author, Book book) {
        var books = bookDAO.getBookByAuthor(Collections.singletonList(author));
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros para el autor: " + author);
        } else {
            System.out.println("Libros del autor " + author + ":");
            books.forEach(b -> System.out.println(b));
        }
    }
}