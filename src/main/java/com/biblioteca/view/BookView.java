package com.biblioteca.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.biblioteca.controller.BookController;
import com.biblioteca.model.Book;
import com.biblioteca.utils.LoggerConfig;

public class BookView {
    private static final Logger logger = LoggerConfig.getLogger(BookView.class.getName(), "logs/bookview.log");
    private final BookController bookController;

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
            logger.info("Libro creado: " + title);
            System.out.println("El libro ha sido añadido correctamente.");
        } catch (Exception e) {
            logger.severe("Error al crear el libro: " + e.getMessage());
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
                logger.warning("Intento de eliminar un libro con un ID no válido.");
                return;
            }

            int id = scanner.nextInt();

            // Llamar al método deleteBook del controlador
            boolean success = bookController.deleteBook(id);

            if (success) {
                logger.info("Libro eliminado con ID: " + id);
                System.out.println("El libro ha sido eliminado correctamente.");
            } else {
                logger.warning("No se encontró un libro con el ID proporcionado: " + id);
                System.out.println("No se encontró un libro con el ID proporcionado.");
            }
        } catch (Exception e) {
            logger.severe("Error al eliminar el libro: " + e.getMessage());
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
                logger.info("Libro actualizado con ID: " + id);
                System.out.println("El libro ha sido actualizado correctamente.");
            } else {
                logger.warning("No se encontró un libro con el ID proporcionado: " + id);
                System.out.println("No se encontró un libro con el ID proporcionado.");
            }
        } catch (Exception e) {
            logger.severe("Error al actualizar el libro: " + e.getMessage());
        } finally {
            scanner.close(); // Cerrar el escáner
        }
    }

    /**
     * Imprime una lista de libros en un formato tabular limpio y ordenado.
     *
     * @param books Lista de libros a imprimir.
     */
    private void printBooksTable(List<Book> books) {
        if (books.isEmpty()) {
            logger.info("No se encontraron libros para mostrar.");
            System.out.println("No se encontraron libros.");
            return;
        }

        // Código ANSI para el color lila
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_RESET = "\u001B[0m";

        // Imprimir encabezados en color lila
        System.out.printf(ANSI_PURPLE + "%-5s %-30s %-20s %-15s %-20s %-10s %-10s %-15s%n" + ANSI_RESET,
                "ID", "Título", "Autor(es)", "ISBN", "Género(s)", "Páginas", "Año", "Editorial");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Imprimir cada libro en formato tabular
        for (Book book : books) {
            System.out.printf("%-5d %-30s %-20s %-15d %-20s %-10d %-10d %-15s%n",
                    book.getId(),
                    book.getTitle(),
                    String.join(", ", book.getAuthor()),
                    book.getIsbn(),
                    String.join(", ", book.getGenre()),
                    book.getPages(),
                    book.getYear(),
                    book.getPublisher());
        }
        logger.info("Se imprimieron " + books.size() + " libros.");
    }

    public void getAllBooks() {
        List<Book> books = bookController.getAllBooks();
        printBooksTable(books);
    }

    public void getBooksByGenre() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese el género del libro: ");
            String genre = scanner.nextLine();

            List<Book> books = bookController.getBooksByGenre(genre);
            printBooksTable(books);
        } finally {
            scanner.close();
        }
    }

    public void searchBookByTitle() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese el título o parte del título del libro: ");
            String title = scanner.nextLine();

            List<Book> books = bookController.searchBookByTitle(title);
            printBooksTable(books);
        } finally {
            scanner.close();
        }
    }

    public void getBookByAuthor() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese el nombre del autor: ");
            String author = scanner.nextLine();

            List<Book> books = bookController.getBooksByAuthor(author);
            printBooksTable(books);
        } finally {
            scanner.close();
        }
    }
}