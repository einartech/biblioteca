package com.biblioteca;

import com.biblioteca.model.Book;
import com.biblioteca.model.BookDAO;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Crear un nuevo objeto Book
        Book book = new Book(
                "The Grewerat CAROL", // title
                Arrays.asList("F. Scott CAROL"), // author
                "A classic CAROL set in the Jazz Age.", // description
                978074365, // isbn
                Arrays.asList("EINwCAROLrAR", "s"), // gender
                180 // pages
        );

        // Crear una instancia de BookDAO
        BookDAO bookDAO = new BookDAO();

        // Llamar al método createBook para insertar el libro en la base de datos
        bookDAO.createBook(book);

        // Mensaje de confirmación
        System.out.println("El libro ha sido insertado en la base de datos.");
    }
}
