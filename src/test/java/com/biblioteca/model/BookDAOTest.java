package com.biblioteca.model;

import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookDAOTest {
    private BookDAO bookDAO;

    @BeforeAll
    void setup() {
        // Inicializar el DAO
        bookDAO = new BookDAO();

        // Configurar la base de datos de prueba (opcional)
        // Por ejemplo, puedes usar una base de datos en memoria o limpiar la base de
        // datos existente.
    }

    @BeforeEach
    void cleanDatabase() {
        // Limpia la base de datos antes de cada prueba
        List<Book> books = bookDAO.getAllBooks();
        for (Book book : books) {
            bookDAO.deleteBook(book.getId());
        }
    }

    @Test
    void testCreateBook() {
        // Crear un libro
        Book book = new Book("Test Title", List.of("Author1", "Author2"), "Test Description", 1234567890123L,
                List.of("Genre1", "Genre2"), 300, 2023, "Test Publisher");

        bookDAO.createBook(book);

        // Verificar que el libro fue creado
        List<Book> books = bookDAO.getAllBooks();
        assertEquals(1, books.size());
        assertEquals("Test Title", books.get(0).getTitle());
    }

    @Test
    void testGetAllBooks() {
        // Crear varios libros
        Book book1 = new Book("Title1", List.of("Author1"), "Description1", 1111111111111L,
                List.of("Genre1"), 100, 2021, "Publisher1");
        Book book2 = new Book("Title2", List.of("Author2"), "Description2", 2222222222222L,
                List.of("Genre2"), 200, 2022, "Publisher2");

        bookDAO.createBook(book1);
        bookDAO.createBook(book2);

        // Obtener todos los libros
        List<Book> books = bookDAO.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    void testUpdateBook() {
        // Crear un libro
        Book book = new Book("Original Title", List.of("Author1"), "Original Description", 1234567890123L,
                List.of("Genre1"), 300, 2023, "Original Publisher");
        bookDAO.createBook(book);

        // Obtener el libro creado
        List<Book> books = bookDAO.getAllBooks();
        Book bookToUpdate = books.get(0);

        // Actualizar el libro
        bookToUpdate.setTitle("Updated Title");
        bookToUpdate.setDescription("Updated Description");
        boolean success = bookDAO.updateBook(bookToUpdate);

        // Verificar que la actualización fue exitosa
        assertTrue(success);
        List<Book> updatedBooks = bookDAO.getAllBooks();
        assertEquals("Updated Title", updatedBooks.get(0).getTitle());
        assertEquals("Updated Description", updatedBooks.get(0).getDescription());
    }

    @Test
    void testDeleteBook() {
        // Crear un libro
        Book book = new Book("Test Title", List.of("Author1"), "Test Description", 1234567890123L,
                List.of("Genre1"), 300, 2023, "Test Publisher");
        bookDAO.createBook(book);

        // Obtener el libro creado
        List<Book> books = bookDAO.getAllBooks();
        assertEquals(1, books.size());

        // Eliminar el libro
        boolean success = bookDAO.deleteBook(books.get(0).getId());

        // Verificar que el libro fue eliminado
        assertTrue(success);
        List<Book> remainingBooks = bookDAO.getAllBooks();
        assertTrue(remainingBooks.isEmpty());
    }
}
