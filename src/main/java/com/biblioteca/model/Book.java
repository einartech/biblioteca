package com.biblioteca.model;

import java.util.List;

/**
 * Clase que representa un libro en la biblioteca.
 */
public class Book {
    // Atributos
    private int id;
    private String title;
    private List<String> author;
    private String description;
    private long isbn;
    private List<String> genre;
    private int pages;
    private int year;
    private String publisher;

    // Constructor sin parámetros
    public Book() {
    }

    // Constructor completo
    public Book(int id, String title, List<String> author, String description, long isbn, List<String> genre, int pages,
            int year, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.genre = genre;
        this.pages = pages;
        this.year = year;
        this.publisher = publisher;
    }

    // Constructor sin ID (para nuevos libros)
    public Book(String title, List<String> author, String description, long isbn, List<String> genre, int pages,
            int year, String publisher) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.genre = genre;
        this.pages = pages;
        this.year = year;
        this.publisher = publisher;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        if (isbn <= 0) {
            throw new IllegalArgumentException("El ISBN debe ser un número positivo.");
        }
        this.isbn = isbn;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages < 0) {
            throw new IllegalArgumentException("El número de páginas no puede ser negativo.");
        }
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", description='" + description + '\'' +
                ", isbn=" + isbn +
                ", genre=" + genre +
                ", pages=" + pages +
                ", year=" + year +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
