package com.biblioteca;

import com.biblioteca.controller.BookController;
import com.biblioteca.view.BookView;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
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

        Scanner scanner = new Scanner(System.in);
        int seleccion = -1;

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
            case 2:
                bookView.createBook();
                break;
            default:
                System.out.println("Funcionalidad no implementada.");
        }
    }
}
