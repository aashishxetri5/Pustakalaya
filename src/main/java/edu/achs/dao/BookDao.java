/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.dao;

import edu.achs.entities.Books;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aashish Katwal
 */
public interface BookDao {

    public void addBook(Books book);

    public List<Books> getAllBooks();

    public List<Books> getBorrowedBooks(int userId);

    public List<Books> getAllBorrowedBooks();

    public void updateBook(Books book);

    public void deleteBook(int bookId);

    public List<Books> getSearchedBookDetail(int id);

    public ArrayList<String> getAllGenres();
}
