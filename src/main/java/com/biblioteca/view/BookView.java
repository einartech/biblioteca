package com.biblioteca.view;

import java.util.Arrays;
import java.util.Scanner;

import com.biblioteca.controller.BookController;
import com.biblioteca.model.Book;

public class BookView {
    private final BookController bookController;

    // Constructor que recibe el controlador
    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    // Método para añadir un libro
    public void createBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();

        System.out.print("Ingrese los autores (separados por comas): ");
        String[] authors = scanner.nextLine().split(",");

        System.out.print("Ingrese la descripción del libro: ");
        String description = scanner.nextLine();

        System.out.print("Ingrese el ISBN del libro: ");
        long isbn = scanner.nextLong();

        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese los géneros (separados por comas): ");
        String[] genres = scanner.nextLine().split(",");

        System.out.print("Ingrese el número de páginas: ");
        int pages = scanner.nextInt();

        // Crear un objeto Book y pasarlo al controlador
        Book book = new Book(title, Arrays.asList(authors), description, isbn, Arrays.asList(genres), pages);
        bookController.createBook(book);

        System.out.println("El libro ha sido añadido correctamente.");
    }

    // Método para eliminar un libro
    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ISBN del libro que desea eliminar: ");
        if (!scanner.hasNextLong()) {
            System.out.println("El ISBN ingresado no es válido. Intente nuevamente.");
            return;
        }

        long isbn = scanner.nextLong();

        // Llamar al método deleteBook del controlador
        bookController.deleteBook(isbn);

        System.out.println("Operación de eliminación finalizada.");
    }

    // Método para ver todos los libros
    public void getAllBooks() {
        // Llamar al método getAllBooks del controlador
        bookController.getAllBooks();
    }

    // Método para ver un libro por autor
    public void getBookByAuthor() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del autor: ");
        String author = scanner.nextLine();

        // Llamar al método getBookByAuthor del controlador
        bookController.getBookByAuthor(author);

        System.out.println("Operación de búsqueda finalizada.");
    }
}