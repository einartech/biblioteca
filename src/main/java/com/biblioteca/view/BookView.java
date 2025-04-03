package com.biblioteca.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.biblioteca.controller.BookController;
import com.biblioteca.model.Book;

public class BookView {
    private final BookController bookController;

    
    public BookView(BookController bookController) {
        this.bookController = bookController;
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
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese la editorial: ");
        String publisher = scanner.nextLine();

        System.out.print("Ingrese el año de publicación: ");
        int year = scanner.nextInt();

       
        Book book = new Book(
                title,
                Arrays.asList(authors),
                description,
                isbn,
                Arrays.asList(genres),
                pages,
                year,
                publisher);

        
        bookController.createBook(book);

        System.out.println("El libro ha sido añadido correctamente.");
        scanner.close();

    }

    
    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese el id del libro que desea eliminar: ");

            if (!scanner.hasNextLong()) {
                System.out.println("El ID ingresado no es válido. Intente nuevamente.");
                return;
            }

            int id = scanner.nextInt();

           
            bookController.deleteBook(id);

            System.out.println("Operación de eliminación finalizada.");
        } finally {
            scanner.close(); 
        }
    }

    public void updateBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del libro que desea actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Nuevo título: ");
        String title = scanner.nextLine();

        System.out.print("Nuevos autores (separados por comas): ");
        String[] authors = scanner.nextLine().split(",");

        System.out.print("Nueva descripción: ");
        String description = scanner.nextLine();

        System.out.print("Nuevo ISBN: ");
        long isbn = scanner.nextLong();
        scanner.nextLine(); 

        System.out.print("Nuevos géneros (separados por comas): ");
        String[] genres = scanner.nextLine().split(",");

        System.out.print("Nuevo número de páginas: ");
        int pages = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Nueva editorial: ");
        String publisher = scanner.nextLine();

        System.out.print("Nuevo año de publicación: ");
        int year = scanner.nextInt();

        
        List<String> authorList = Arrays.asList(authors);
        List<String> genreList = Arrays.asList(genres);

        
        Book book = new Book(title, authorList, description, isbn, genreList, pages, year, publisher);
        book.setId(id); 

        
        bookController.updateBook(book);
        System.out.println("El libro ha sido actualizado correctamente.");
        scanner.close(); 

    }

   
    public void getAllBooks() {
        
        bookController.getAllBooks();
    }

    
    public void getBooksByGenre() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el género del libro: ");
        String genre = scanner.nextLine();

        
        bookController.getBooksByGenre(genre);
        scanner.close(); 

        System.out.println("Operación de búsqueda por género finalizada.");
    }

    public void searchBookByTitle() {
        Scanner scanner = new Scanner(System.in); 

        System.out.print("Ingrese el título o parte del título del libro a buscar: ");
        String title = scanner.nextLine();

        List<Book> books = bookController.searchBookByTitle(title);

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            System.out.println("Libros encontrados:");

            for (Book book : books) {
                System.out.println("---------------------------");

                System.out.println("ID: " + book.getId());
                System.out.println("Título: " + book.getTitle());
                System.out.println("Descripción: " + book.getDescription());
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("Autores: " + String.join(", ", book.getAuthor()));
                System.out.println("Géneros: " + String.join(", ", book.getGenre()));
                System.out.println("Páginas: " + book.getPages());
                System.out.println("Editorial: " + book.getPublisher());
                System.out.println("Año: " + book.getYear());
                System.out.println("---------------------------");
            }
            scanner.close();

        }
    }

    
    public void getBookByAuthor() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del autor: ");
        String author = scanner.nextLine();

        
        bookController.getBookByAuthor(author, null);
        scanner.close(); 
    }
}