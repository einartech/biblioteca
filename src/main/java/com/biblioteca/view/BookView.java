package com.biblioteca.view;

import java.util.Arrays;
import java.util.Scanner;

import com.biblioteca.controller.BookController;
import com.biblioteca.model.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookView {
    private final BookController bookController;

    // Constructor que recibe el controlador
    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    // // Método para añadir un libro
    // public void createBook() {
    //     Scanner scanner = new Scanner(System.in);

    //     System.out.print("Ingrese el título del libro: ");
    //     String title = scanner.nextLine();

    //     System.out.print("Ingrese los autores (separados por comas): ");
    //     String[] authors = scanner.nextLine().split(",");

    //     System.out.print("Ingrese la descripción del libro: ");
    //     String description = scanner.nextLine();

    //     System.out.print("Ingrese el ISBN del libro: ");
    //     long isbn = scanner.nextLong();

    //     scanner.nextLine(); // Limpiar el buffer

    //     System.out.print("Ingrese los géneros (separados por comas): ");
    //     String[] genres = scanner.nextLine().split(",");

    //     System.out.print("Ingrese el número de páginas: ");
    //     int pages = scanner.nextInt();

        // System.out.print("Ingrese año publicacion");
        // int year = scanner.nextInt();

    //     // Crear un objeto Book y pasarlo al controlador
    //     Book book = new Book(title, Arrays.asList(authors), description, isbn, Arrays.asList(genres), pages,);
    //     bookController.createBook(book);

    //     System.out.println("El libro ha sido añadido correctamente.");
    // }

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

     public void searchBookByTitle() {
        Scanner scanner = new Scanner(System.in);  // Declaración del Scanner

        System.out.print("Ingrese el título o parte del título del libro a buscar: ");
        String title = scanner.nextLine();

        List<Book> books = bookController.searchBookByTitle(title);

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            System.out.println("Libros encontrados:");
            for (Book book : books) {
                System.out.println("ID: " + book.getId());
                System.out.println("Título: " + book.getTitle());
                System.out.println("Descripción: " + book.getDescription());
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("Autores: " + String.join(", ", book.getAuthor()));  
                System.out.println("Géneros: " + String.join(", ", book.getGender()));  
                System.out.println("Páginas: " + book.getPages());
            }
        }
      }


    public void updateBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del libro que desea actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Nuevo título: ");
        String title = scanner.nextLine();

        System.out.print("Nueva descripción: ");
        String description = scanner.nextLine();

        System.out.print("Nuevo ISBN: ");
        long isbn = scanner.nextLong();

        System.out.print("Nuevo número de páginas: ");
        int pages = scanner.nextInt();

        // Crear un objeto Book con los datos actualizados
        Book book = new Book(title, null, description, isbn, null, pages);
        book.setId(id); // Establecer el ID del libro

        // Llamar al controlador para actualizar el libro
        bookController.updateBook(book);
    }

    // Método para ver todos los libros
    public void getAllBooks() {
        // Llamar al método getAllBooks del controlador
        bookController.getAllBooks();
    }

    // Método para ver libros por género
    public void getBooksByGenre() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el género del libro: ");
        String genre = scanner.nextLine();

        // Crear un objeto Book con el género proporcionado
        Book book = new Book(null, null, null, 0, Arrays.asList(genre), 0);

        // Llamar al método getBooksByGenre del controlador
        bookController.getBooksByGenre(genre);

        scanner.close(); // Cerrar el escáner
        System.out.println("Operación de búsqueda por género finalizada.");
    }

    // Método para ver un libro por autor
    public void getBookByAuthor() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del autor: ");
        String author = scanner.nextLine();

        // Llamar al método getBookByAuthor del controlador
        bookController.getBookByAuthor(author, null);
    }
}