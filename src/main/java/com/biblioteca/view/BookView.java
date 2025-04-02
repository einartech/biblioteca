package com.biblioteca.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.biblioteca.controller.BookController;
import com.biblioteca.model.Book;

public class BookView {
    private final BookController bookController;

    // Constructor que recibe el controlador
    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    /**
     * Captura los datos de un libro y lo crea en la base de datos.
     */
    public void createBook() {
        Scanner scanner = new Scanner(System.in);
        try {
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
            scanner.nextLine(); // Limpiar el buffer

            System.out.print("Ingrese la editorial: ");
            String publisher = scanner.nextLine();

            System.out.print("Ingrese el año de publicación: ");
            int year = scanner.nextInt();

            // Crear un objeto Book con los datos ingresados
            Book book = new Book(
                    title,
                    Arrays.asList(authors),
                    description,
                    isbn,
                    Arrays.asList(genres),
                    pages,
                    year,
                    publisher);

            // Enviar el libro al controlador
            bookController.createBook(book);

            System.out.println("El libro ha sido añadido correctamente.");
        } finally {
            scanner.close(); // Cerrar el escáner
        }
    }

    /**
     * Elimina un libro por su ID.
     */
    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese el id del libro que desea eliminar: ");

            if (!scanner.hasNextInt()) {
                System.out.println("El ID ingresado no es válido. Intente nuevamente.");
                return;
            }

            int id = scanner.nextInt();

            // Llamar al método deleteBook del controlador
            boolean success = bookController.deleteBook(id);

            if (success) {
                System.out.println("El libro ha sido eliminado correctamente.");
            } else {
                System.out.println("No se encontró un libro con el ID proporcionado.");
            }
        } finally {
            scanner.close(); // Cerrar el escáner
        }
    }

    /**
     * Actualiza los datos de un libro existente.
     */
    public void updateBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese el ID del libro que desea actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            System.out.print("Nuevo título: ");
            String title = scanner.nextLine();

            System.out.print("Nuevos autores (separados por comas): ");
            String[] authors = scanner.nextLine().split(",");

            System.out.print("Nueva descripción: ");
            String description = scanner.nextLine();

            System.out.print("Nuevo ISBN: ");
            long isbn = scanner.nextLong();
            scanner.nextLine(); // Limpiar el buffer

            System.out.print("Nuevos géneros (separados por comas): ");
            String[] genres = scanner.nextLine().split(",");

            System.out.print("Nuevo número de páginas: ");
            int pages = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            System.out.print("Nueva editorial: ");
            String publisher = scanner.nextLine();

            System.out.print("Nuevo año de publicación: ");
            int year = scanner.nextInt();

            // Crear un objeto Book con los datos actualizados
            Book book = new Book(title, Arrays.asList(authors), description, isbn, Arrays.asList(genres), pages, year,
                    publisher);
            book.setId(id); // Establecer el ID del libro

            // Llamar al controlador para actualizar el libro
            boolean success = bookController.updateBook(book);
            if (success) {
                System.out.println("El libro ha sido actualizado correctamente.");
            } else {
                System.out.println("No se encontró un libro con el ID proporcionado.");
            }
        } finally {
            scanner.close(); // Cerrar el escáner
        }
    }

    /**
     * Muestra todos los libros.
     */
    public void getAllBooks() {
        List<Book> books = bookController.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No hay libros disponibles.");
        } else {
            books.forEach(System.out::println);
        }
    }

    /**
     * Muestra libros por género.
     */
    public void getBooksByGenre() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese el género del libro: ");
            String genre = scanner.nextLine();

            // Llamar al método getBooksByGenre del controlador
            List<Book> books = bookController.getBooksByGenre(genre);

            if (books.isEmpty()) {
                System.out.println("No se encontraron libros para el género: " + genre);
            } else {
                books.forEach(System.out::println);
            }
        } finally {
            scanner.close(); // Cerrar el escáner
        }
    }

    /**
     * Busca libros por título.
     */
    public void searchBookByTitle() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese el título o parte del título del libro: ");
            String title = scanner.nextLine();

            List<Book> books = bookController.searchBookByTitle(title);
            if (books.isEmpty()) {
                System.out.println("No se encontraron libros con el título: " + title);
            } else {
                books.forEach(System.out::println);
            }
        } finally {
            scanner.close(); // Cerrar el escáner
        }
    }

    /**
     * Muestra libros por autor.
     */
    public void getBookByAuthor() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese el nombre del autor: ");
            String author = scanner.nextLine();

            // Llamar al método getBooksByAuthor del controlador
            List<Book> books = bookController.getBooksByAuthor(author);

            if (books.isEmpty()) {
                System.out.println("No se encontraron libros para el autor: " + author);
            } else {
                books.forEach(System.out::println);
            }
        } finally {
            scanner.close(); // Cerrar el escáner
        }
    }
}