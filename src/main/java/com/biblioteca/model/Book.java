package com.biblioteca.model;

import java.util.List;

public class Book {

    // PROPIEDADESD DE LA ENTIDAD
    private int id;
    private String title;
    private List<String> author;
    private String description;
    private int isbn;
    private List<String> gender;
    private int pages;

    // CONSTRUCTOR DE LA ENTIDAD
    public Book(String title, List<String> author, String description, int isbn, List<String> gender, int pages) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.gender = gender;
        this.pages = pages;
    }

    // GETTERS DE LA ENTIDAD
    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public List getAuthor() {
        return this.author;
    }

    public String getDescription() {
        return this.description;
    }

    public int getIsbn() {
        return this.isbn;
    }

    public List getGender() {
        return this.gender;
    }

    public int getPages() {
        return this.pages;
    }

    // SETTERS DE LA ENTIDAD

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

}
