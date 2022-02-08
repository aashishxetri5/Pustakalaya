/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.dao;

import java.time.LocalDate;

/**
 *
 * @author Aashish Katwal
 */
public interface BorrowDao {

    public boolean canUserBorrowBook(int userId);

    public boolean isBookInStock(String bookId);

    public void borrowBookProcess(int userId, String bookId);

    public void changeNumberOfStock(String bookId, String action);
    
    public void updateFrequencyOfBookBorrowed(String bookId);

    public boolean isBookBorrowedByUser(int userId, String bookId);
    
    public boolean hasBookBeenReturned(int userId, String bookId);

    public LocalDate getIssuedDate(int userId, String bookId);

    public void returnBookProcess(String bookId, int userId);
    
    public void determineFineAmount(int borrowerId, String bookId);
}
