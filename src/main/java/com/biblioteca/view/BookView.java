package com.biblioteca.view;

import com.biblioteca.controller.BookController;
import com.biblioteca.model.Book;

import java.util.Arrays;
import java.util.Scanner;

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

        System.out.print("Ingrese año publicacion");
        int year = scanner.nextInt();

        // Crear un objeto Book y pasarlo al controlador
        Book book = new Book(title, Arrays.asList(authors), description, isbn, Arrays.asList(genres), pages, year);
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

    public void updateBook() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Ingrese el ID del libro a actualizar: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Nuevo título del libro: ");
        String title = scanner.nextLine();
    
        System.out.print("Nuevos autores (separados por comas): ");
        String[] authors = scanner.nextLine().split(",");
    
        System.out.print("Nueva descripción del libro: ");
        String description = scanner.nextLine();
    
        System.out.print("Nuevo ISBN: ");
        long isbn = scanner.nextLong();
        scanner.nextLine();
    
        System.out.print("Nuevos géneros (separados por comas): ");
        String[] genres = scanner.nextLine().split(",");
    
        System.out.print("Nuevo número de páginas: ");
        int pages = scanner.nextInt();
    
        System.out.print("Nuevo año de publicación: ");
        int year = scanner.nextInt();
    
        // Crear el objeto Book actualizado
        Book book = new Book(bookId, title, Arrays.asList(authors), description, isbn, Arrays.asList(genres), pages, year);
    
        // Llamar al método del controlador para hacer la actualización
        bookController.updateBook(book);
    
        System.out.println("El libro ha sido actualizado correctamente.");
    }
}