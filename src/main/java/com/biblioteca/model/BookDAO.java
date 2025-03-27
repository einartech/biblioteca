package com.biblioteca.model;

import java.sql.Connection;
import java.sql.PreparedStatement;


import com.biblioteca.config.DBManager;

public class BookDAO {

    private PreparedStatement stmn;
    private Connection connection;

    public void createBook(Book book){
        try {
            connection = DBManager.initConnection();
            String sql = "INSERT INTO books (title, author, description, isbn, gender, pages) VALUES (?,?,?,?,?,?)";
            stmn = connection.prepareStatement(sql);
            stmn.setString(1, book.getTitle());
            Array authorArray = conn.createArrayOf("text", book.getAuthor().toArray());
            stmt.setArray(2, authorArray);

        }
    }
    
}
