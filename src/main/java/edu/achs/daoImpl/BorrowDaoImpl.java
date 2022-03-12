/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.daoImpl;

import edu.achs.dao.BorrowDao;
import edu.achs.utility.DBConnection;
import edu.achs.utility.GenerateDates;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aashish Katwal
 */
public class BorrowDaoImpl implements BorrowDao {

    private String sqlQuery = "";
    private final double FINE_RATE = 50.0;

    /**
     * This function returns true if a user has borrowed less than 5 books which
     * are yet to be returned and false if exactly 5 books are yet to be
     * returned.
     *
     * @param userId
     * @return
     */
    @Override
    public boolean canUserBorrowBook(int userId) {
        try {
            sqlQuery = "select count(*) from tbl_borrow where userId = ? and return_status = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, "pending");
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.getInt(1) >= 5) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Checks if the book is in stock. Returns true if in stock and false if
     * not. This function called when user clicks borrow button
     *
     * @param bookId
     * @return
     */
    @Override
    public boolean isBookInStock(String bookId) {
        try {
            sqlQuery = "select tbl_books.stock, tbl_bookstock.remaining_stock from tbl_books inner join tbl_bookstock where "
                    + "tbl_books.bookId =? and tbl_bookstock.bookId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, bookId);
            pst.setString(2, bookId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getInt("remaining_stock") <= rs.getInt("stock") && rs.getInt("remaining_stock") != 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Adds the record of the borrowed book in the database.
     *
     * @param userId
     * @param bookId
     */
    @Override
    public void borrowBookProcess(int userId, String bookId) {
        try {
            sqlQuery = "insert into tbl_borrow (userId, bookId, issue_date, return_status) values (?, ?, ?, ?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, bookId);
            pst.setDate(3, new GenerateDates().getCurrentDate());
            pst.setString(4, "pending");
            pst.executeUpdate();
            changeNumberOfStock(bookId, "Borrow");
            updateFrequencyOfBookBorrowed(bookId);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reduces available stock of book by 1 when a book is borrowed.
     *
     * @param bookId
     * @param action
     */
    @Override
    public void changeNumberOfStock(String bookId, String action) {
        try {
            sqlQuery = "select remaining_stock from tbl_bookstock where bookId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, bookId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                sqlQuery = "update tbl_bookstock set remaining_stock = ? where bookId = ?";
                pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
                if (action.equals("Borrow")) {
                    pst.setInt(1, rs.getInt("remaining_stock") - 1);
                } else if (action.equals("Return")) {
                    pst.setInt(1, rs.getInt("remaining_stock") + 1);
                }
                pst.setString(2, bookId);
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param bookId
     */
    @Override
    public void updateFrequencyOfBookBorrowed(String bookId) {
        try {
            sqlQuery = "update tbl_borrowcount set borrow_times = borrow_times+1 where bookId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, bookId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param userId
     * @param bookId
     * @return
     */
    @Override
    public boolean isBookBorrowedByUser(int userId, String bookId) {
        try {
            sqlQuery = "select count(*) from tbl_borrow where userId = ? and bookId = ? and return_status = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, bookId);
            pst.setString(3, "pending");
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Checks if the book being returned is already returned. Returns 'true' if
     * book has already been returned and 'false' if book has not been returned
     * yet.
     *
     * @param userId
     * @param bookId
     * @return
     */
    @Override
    public boolean hasBookBeenReturned(int userId, String bookId) {
        try {
            sqlQuery = "select return_status from tbl_borrow where userId = ? and bookId = ? and return_date is null";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, bookId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getString("return_status").equals("returned")) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param userId
     * @param bookId
     * @return
     */
    @Override
    public LocalDate getIssuedDate(int userId, String bookId) {
        try {
            sqlQuery = "select issue_date from tbl_borrow where userId = ? and bookId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, bookId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return LocalDate.parse(rs.getDate("issue_date").toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param bookId
     * @param userId
     */
    @Override
    public void returnBookProcess(String bookId, int userId) {
        try {
            sqlQuery = "update tbl_borrow set return_date = ?, return_status = ? where bookId = ? and userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setDate(1, new GenerateDates().getCurrentDate());
            pst.setString(2, "returned");
            pst.setString(3, bookId);
            pst.setInt(4, userId);
            pst.executeUpdate();
            changeNumberOfStock(bookId, "Return");
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param borrowerId
     * @param bookId
     */
    @Override
    public void determineFineAmount(int borrowerId, String bookId) {
        try {
            Period interval = Period.between(getIssuedDate(borrowerId, bookId), LocalDate.parse(new GenerateDates().getCurrentDate().toString()));

            sqlQuery = "update tbl_userdetails set fine = ? where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            if (interval.getDays() > 10) {
                pst.setDouble(1, interval.getDays() * FINE_RATE);
            } else {
                pst.setDouble(1, 0.0);
            }
            pst.setInt(2, borrowerId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
