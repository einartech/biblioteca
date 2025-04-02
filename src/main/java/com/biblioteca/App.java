package com.biblioteca;

import java.util.Scanner;

import com.biblioteca.controller.BookController;
import com.biblioteca.view.BookView;

public class App {
    public static void main(String[] args) {
        // Mostrar introducción del programa
        mostrarIntroduccion();

        // Instanciar el controlador y la vista
        BookController bookController = new BookController();
        BookView bookView = new BookView(bookController);

        // Opciones del menú
        String[] opciones = {
                "1. Ver todos los libros",
                "2. Añadir un libro",
                "3. Editar un libro",
                "4. Eliminar un libro por id",
                "5. Buscar un libro por título",
                "6. Buscar un libro por autor",
                "7. Buscar un libro por género literario",
                "8. Salir del programa"
        };

        Scanner scanner = new Scanner(System.in);
        int seleccion;

        // Mostrar el menú y capturar la selección del usuario
        while (true) {
            System.out.println("\nSeleccione una opción ingresando un número (1-8):");
            for (String opcion : opciones) {
                System.out.println(opcion);
            }

            System.out.print("Ingrese su elección: ");
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 1 && seleccion <= 8) {
                    // Ejecutar la opción seleccionada
                    switch (seleccion) {
                        case 1:
                            bookView.getAllBooks(); // Llamar al método getAllBooks de BookView
                            break;
                        case 2:
                            bookView.createBook(); // Llamar al método createBook de BookView
                            break;
                        case 3:
                            bookView.updateBook(); // Llamar al método updateBook de BookView
                            break;
                        case 4:
                            bookView.deleteBook(); // Llamar al método deleteBook de BookView
                            break;
                        case 5:
                            bookView.searchBookByTitle(); // Llamar al método searchBookByTitle de BookView
                            break;
                        case 6:
                            bookView.getBookByAuthor(); // Llamar al método getBookByAuthor de BookView
                            break;
                        case 7:
                            bookView.getBooksByGenre(); // Llamar al método getBooksByGenre de BookView
                            break;
                        case 8:
                            System.out.println("Saliendo del programa. ¡Hasta luego!");
                            scanner.close(); // Cerrar el scanner
                            System.exit(0); // Terminar el programa
                            break;
                        default:
                            System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    }
                } else {
                    System.out.println("Por favor, ingrese un número válido entre 1 y 8.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar la entrada inválida
            }
        }
    }

    /**
     * Muestra una introducción del programa con un dibujo ASCII.
     */
    private static void mostrarIntroduccion() {
        System.out.println("\n ");

        System.out.println("***********************************************");
        System.out.println("*                                             *");
        System.out.println("*          📚 SISTEMA DE BIBLIOTECA 📚         *");
        System.out.println("*                                             *");
        System.out.println("***********************************************");
        System.out.println("\nBienvenido al sistema de gestión de libros.");
        System.out.println("Este programa te permitirá realizar operaciones CRUD");
        System.out.println("en una base de datos de libros, como agregar, buscar,");
        System.out.println("actualizar y eliminar libros.\n");

        // Dibujo ASCII de un libro
        System.out.println("          _________________________");
        System.out.println("         |                         |");
        System.out.println("         |   📚  BIBLIOTECA 📚      |");
        System.out.println("         |_________________________|");
        System.out.println("         |  [ ]   [ ]   [ ]   [ ]  |");
        System.out.println("         |  [ ]   [ ]   [ ]   [ ]  |");
        System.out.println("         |  [ ]   [ ]   [ ]   [ ]  |");
        System.out.println("         |  [ ]   [ ]   [ ]   [ ]  |");
        System.out.println("         |  [ ]   [ ]   [ ]   [ ]  |");
        System.out.println("         |_________________________|");

        // Pausa de 3 segundos antes de continuar
        try {
            Thread.sleep(3000); // Pausa de 3000 ms (3 segundos)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}