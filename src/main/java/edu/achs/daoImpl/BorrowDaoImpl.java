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
     *
     * @param userId
     * @return
     */
    @Override
    public boolean canUserBorrowBook(int userId) {
        try {
            sqlQuery = "select count(*) from tbl_borrow where userId = ? and status = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, "pending");
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.getInt(1) > 5) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
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
     * @param userId
     * @param bookId
     */
    @Override
    public void borrowBookProcess(int userId, String bookId) {
        try {
            sqlQuery = "insert into tbl_borrow values (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, bookId);
//            pst.setDate(3, new GenerateDates().getIssuedDate());
//            pst.setDate(4, new GenerateDates().getReturnDate());
            pst.setString(5, "pending");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
