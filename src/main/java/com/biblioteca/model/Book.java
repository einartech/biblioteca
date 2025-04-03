package com.biblioteca.model;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private List<String> author;
    private String description;
    private long isbn;
    private List<String> genre;
    private int pages;
    private int year;
    private String publisher;

    public Book() {
    }

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

    public Book(int id, String title, List<String> author, String description,
            long isbn, List<String> genre,
            String publisher, int pages) {
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
        this.pages = pages;
    }

    public String toString() {
        return this.title;
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
}
