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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aashish Katwal
 */
public class BorrowDaoImpl implements BorrowDao {

    String sqlQuery = "";

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
            System.out.println("NUM: " + rs.getInt(1));
            if (rs.getInt(1) >= 5) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     *
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
            rs.next();
            System.out.println("REM:: " + rs.getInt("remaining_stock"));
            System.out.println("Actual Stock:: " + rs.getInt("stock"));
            if (rs.getInt("remaining_stock") <= rs.getInt("stock") && rs.getInt("remaining_stock") != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     *
     * @param userId
     * @param bookId
     */
    @Override
    public void borrowBookProcess(int userId, String bookId) {
        try {
            sqlQuery = "insert into tbl_borrow (userId, bookId, issue_date, return_date, return_status) values (?, ?, ?, ?, ?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, bookId);
            pst.setDate(3, new GenerateDates().getIssuedDate());
            pst.setDate(4, new GenerateDates().getReturnDate());
            pst.setString(5, "pending");
            pst.executeUpdate();
            System.out.println("REACHED");
            changeNumberOfStock(bookId);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     *
     * @param bookId
     */
    @Override
    public void changeNumberOfStock(String bookId) {
        try {
            sqlQuery = "select remaining_stock from tbl_bookstock where bookId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, bookId);
            ResultSet rs = pst.executeQuery();
            rs.next();

            sqlQuery = "update tbl_bookstock set remaining_stock = ? where bookId = ?";
            pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, rs.getInt("remaining_stock")-1);
            pst.setString(2, bookId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
