package com.biblioteca.view;

import com.biblioteca.controller.BookController;
import com.biblioteca.model.Book;

import java.util.Arrays;
import java.util.Scanner;

public class BookView {
    private final BookController bookController;

    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    public static void main(String[] args) {
        // Instanciar el controlador y la vista
        BookController bookController = new BookController();
        BookView bookView = new BookView(bookController);

        String[] opciones = {
                "1. Ver todos los libros",
                "2. Añadir un libro",
                "3. Editar un libro",
                "4. Eliminar un libro",
                "5. Buscar un libro por título",
                "6. Buscar un libro por autor",
                "7. Buscar un libro por género literario"
        };

        Scanner scanner = new Scanner(System.in); // Para capturar la entrada del usuario
        int seleccion = -1; // Índice de la opción seleccionada

        while (true) {
            // Mostrar el menú
            System.out.println("Seleccione una opción ingresando un número (1-7):");
            for (String opcion : opciones) {
                System.out.println(opcion);
            }

            // Capturar la entrada del usuario
            System.out.print("Ingrese su elección: ");
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();

                // Validar que la selección esté entre 1 y 7
                if (seleccion >= 1 && seleccion <= 7) {
                    break; // Salir del bucle si la entrada es válida
                } else {
                    System.out.println("Por favor, ingrese un número válido entre 1 y 7.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar la entrada inválida
            }
        }

        // Ejecutar la opción seleccionada
        switch (seleccion) {
            case 1:
                System.out.println("Hola, has seleccionado: Ver todos los libros");
                break;
            case 2:
                System.out.println("Hola, has seleccionado: Añadir un libro");
                bookView.createBook(); // Llamar al método createBook de BookView
                break;
            case 3:
                System.out.println("Hola, has seleccionado: Editar un libro");
                break;
            case 4:
                System.out.println("Hola, has seleccionado: Eliminar un libro");
                break;
            case 5:
                System.out.println("Hola, has seleccionado: Buscar un libro por título");
                break;
            case 6:
                System.out.println("Hola, has seleccionado: Buscar un libro por autor");
                break;
            case 7:
                System.out.println("Hola, has seleccionado: Buscar un libro por género literario");
                break;
            default:
                System.out.println("Opción no válida.");
        }

        scanner.close(); // Cerrar el scanner
    }

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

        Book book = new Book(title, Arrays.asList(authors), description, isbn, Arrays.asList(genres), pages);
        bookController.createBook(book);

        System.out.println("El libro ha sido añadido correctamente.");
    }
}
