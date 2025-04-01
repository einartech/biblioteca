package com.biblioteca;

import java.util.Scanner;

import com.biblioteca.controller.BookController;
import com.biblioteca.view.BookView;

public class App {
    public static void main(String[] args) {
        // Instanciar el controlador y la vista
        BookController bookController = new BookController();
        BookView bookView = new BookView(bookController);

        // Opciones del menú
        String[] opciones = {
                "1. Ver todos los libros",
                "2. Añadir un libro",
                "3. Editar un libro",
                "4. Eliminar un libro",
                "5. Buscar un libro por título",
                "6. Buscar un libro por autor",
                "7. Buscar un libro por género literario"
        };

        Scanner scanner = new Scanner(System.in);
        int seleccion;

        // Mostrar el menú y capturar la selección del usuario
        while (true) {
            System.out.println("Seleccione una opción ingresando un número (1-7):");
            for (String opcion : opciones) {
                System.out.println(opcion);
            }

            System.out.print("Ingrese su elección: ");
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
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
                bookController.getAllBooks(); // Llamar al método getAllBooks de BookController
                break;
            case 2:
                bookView.createBook(); // Llamar al método createBook de BookView
                break;
            case 4:
                bookView.deleteBook(); // Llamar al método deleteBook de BookView
                break;
            case 6:
                bookView.getBookByAuthor(); // Llamar al método getBookByAuthor de BookView
                break;
            default:
                System.out.println("Funcionalidad no implementada en App.");
        }

        scanner.close(); // Cerrar el scanner
    }
}
