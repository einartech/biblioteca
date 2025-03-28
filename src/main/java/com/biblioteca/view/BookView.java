package com.biblioteca.view;

import java.util.Scanner;

import com.biblioteca.controller.BookController;
import com.biblioteca.model.Book;

public class BookView {

    private BookController bookController;

    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    public void createBook(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del libro: ");
        String title = scanner.nextLine();

        System.out.println("Ingrese el autor del libro: ");
        String author = scanner.nextLine();

        System.out.println("Ingrese la descripcion del libro: ");
        String description = scanner.nextLine();

        System.out.println("Ingrese el ISBN del libro: ");
        int isbn = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Ingrese el genero del libro: ");
        String gender = scanner.nextLine();

        System.out.println("Ingrese el numero de paginas del libro: ");
        int pages = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book(title, null, description, isbn, null, pages);
        bookController.createBook(book);
        System.out.println("Libro creado con exito!");
        scanner.close();
        
    }

}
