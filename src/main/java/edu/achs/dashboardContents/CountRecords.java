/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.dashboardContents;

import edu.achs.utility.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aashish Katwal
 */
public class CountRecords extends DBConnection {

    String sqlQuery = "";

    /**
     * Has query to count record from the books table in the query and calls a
     * function which executes the query.
     *
     * @return
     */
    public int getTotalBooks() {
        sqlQuery = "select count(*) from tbl_books";
        try {
            return getNumOfRecords(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Has query to count record from the userdetails table in the query and
     * calls a function which executes the query.
     *
     * @return
     */
    public int getTotalMembers() {
        sqlQuery = "select count(*) from tbl_userdetails";
        try {
            return getNumOfRecords(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Has query to count record from the borrow table in the query and calls a
     * function which executes the query.
     *
     * @return
     */
    public int getTotalBorrowedBooks() {
        sqlQuery = "select count(*) from tbl_borrow";
        try {
            return getNumOfRecords(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Has query to count record from the bookrequest table in the query and
     * calls a function which executes the query.
     *
     * @return
     */
    public int getTotalBookRequests() {
        sqlQuery = "select count(*) from tbl_bookrequest";
        try {
            return getNumOfRecords(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Has query to count record from the feedback table in the query and calls
     * a function which executes the query.
     *
     * @return
     */
    public int getTotalFeedbacks() {
        sqlQuery = "select count(*) from tbl_feedback";
        try {
            return getNumOfRecords(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Executes the query and returns the number of records received.
     *
     * @param query
     * @return
     */
    public int getNumOfRecords(String query) throws SQLException {
        try {
            PreparedStatement pst = getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
        }
        return 0;
    }

    public int getPendingBorrowedBooks(int userId) throws SQLException {
        try {
            sqlQuery = "select count(*) from tbl_borrow where userId = ? and return_status = ?";
            PreparedStatement pst = getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, "pending");
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
        }
        return 0;
    }
}
