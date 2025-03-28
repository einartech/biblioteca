package com.biblioteca.controller;

import com.biblioteca.model.Book;
import com.biblioteca.model.BookDAO;

public class BookController {

    BookDAO bookDAO;

    public BookController(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    public void createBook(Book book){
        bookDAO.createBook(book);
    }
    
}
