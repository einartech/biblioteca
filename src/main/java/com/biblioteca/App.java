package com.biblioteca;

import java.util.Scanner;

import com.biblioteca.controller.BookController;
import com.biblioteca.view.BookView;

public class App {
    public static void main(String[] args) {
        
        BookController bookController = new BookController();
        BookView bookView = new BookView(bookController);

        
        String[] opciones = {
                "1. Ver todos los libros",
                "2. Añadir un libro",
                "3. Editar un libro",
                "4. Eliminar un libro por id",
                "5. Buscar un libro por título",
                "6. Buscar un libro por autor",
                "7. Buscar un libro por género literario"
        };

        Scanner scanner = new Scanner(System.in);
        int seleccion;

        
        while (true) {
            System.out.println("Seleccione una opción ingresando un número (1-7):");
            for (String opcion : opciones) {
                System.out.println(opcion);
            }

            System.out.print("Ingrese su elección: ");
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 1 && seleccion <= 7) {
                    break;
                } else {
                    System.out.println("Por favor, ingrese un número válido entre 1 y 7.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next();
            }
        }

        switch (seleccion) {
            case 1:
                bookView.getAllBooks();
                break;
            case 2:
                bookView.createBook();
                break;
            case 3:
                bookView.updateBook();
                break;
            case 4:
                bookView.deleteBook();
                break;
            case 5:
                bookView.searchBookByTitle();
                break;
            case 7:
                bookView.getBooksByGenre();
                break;
            case 6:
                bookView.getBookByAuthor();
                break;
            default:
                System.out.println("Funcionalidad no implementada en App.");
        }

        scanner.close();
    }
}