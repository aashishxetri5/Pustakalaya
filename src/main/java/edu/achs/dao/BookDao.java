/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.dao;

import edu.achs.entities.Books;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aashish Katwal
 */
public interface BookDao {

    public void addBook(Books book);

    public void addStockRecord(String bookId, int stock);

    public void addInBorrowCount(String bookId);

    public List<Books> getAllBooks();

    public List<Books> getBorrowedBooks(int userId);

    public List<Books> getAllBorrowedBooks();

    public Books getDetailsOfBookToBeUpdated(String bookId, String ISBN);

    public void updateBook(Books book);

    public void deleteBook(String bookId);

    public List<Books> getSearchedBookDetail(String bookTitle);

    public void addNewGenre(String genre);

    public boolean doesGenreExist(String genre);

    public Map<Integer, String> getAllGenres();

    public void removeGenre(int id);

    public String getBorrowerName(int userId);
}
