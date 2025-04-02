package com.biblioteca.model;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private List<String> author;
    private String description;
    private long isbn;
    private List<String> gender;
    private int pages;

    // Constructor sin parámetros
    public Book() {
    }

    // Constructor con parámetros
    public Book(String title, List<String> author, String description, long isbn, List<String> gender, int pages) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.gender = gender;
        this.pages = pages;
    }

    public Book(int id, String title, List<String> author, String description, long isbn, List<String> gender,
            int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.gender = gender;
        this.pages = pages;
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
        this.isbn = isbn;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
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
}
