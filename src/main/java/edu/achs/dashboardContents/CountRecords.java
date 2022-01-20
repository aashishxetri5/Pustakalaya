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
public class CountRecords {

    String sqlQuery = "";

    public int getTotalBooks() {
        try {
            sqlQuery = "select count(*) from tbl_books";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalMembers() {
        try {
            sqlQuery = "select count(*) from tbl_userdetails";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalBorrowedBooks() {
        try {
            sqlQuery = "select count(*) from tbl_borrow";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalBookRequests() {
        try {
            sqlQuery = "select count(*) from tbl_bookrequest";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalFeedbacks() {
        try {
            sqlQuery = "select count(*) from tbl_feedback";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CountRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
