package com.biblioteca.controller;

import java.util.Collections;
import java.util.List;

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
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    // Método para ver todos los libros
    public void getAllBooks() {
        bookDAO.getAllBooks();
    }

    public void getBooksByGenre(String genre) {
        bookDAO.getBooksByGenre(genre);
    }

    // Método para buscar por titulo
    public List<Book> searchBookByTitle(String title) {
        return bookDAO.searchBookByTitle(title);
    }

    // Método para ver un libro por autor
    public void getBookByAuthor(String author, Book book) {
        var books = bookDAO.getBookByAuthor(Collections.singletonList(author)); // Obtén la lista de libros
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros para el autor: " + author);
        } else {
            System.out.println("Libros del autor " + author + ":");
            books.forEach(b -> System.out.println(b)); // Imprime cada libro
        }
    }
}