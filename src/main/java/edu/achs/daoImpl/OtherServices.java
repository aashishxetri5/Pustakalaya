/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.daoImpl;

import edu.achs.entities.Books;
import edu.achs.entities.Users;
import edu.achs.utility.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aashish Katwal
 */
public class OtherServices {

    String sqlQuery;

    /**
     * Inserts the data obtained from contact form and saves it in database
     *
     * @param fullname
     * @param email
     * @param message
     */
    public void insertContactInfo(String fullname, String email, String message) {
        try {
            sqlQuery = "insert into tbl_contact (fullname, email, message) values(?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, fullname);
            pst.setString(2, email);
            pst.setString(3, message);
            pst.executeUpdate();
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserts the data obtained from feedback form and saves it in database.
     *
     * @param user
     * @param message
     */
    public void insertFeedbacks(Users user, String message) {
        try {
            sqlQuery = "insert into tbl_feedback values(?,?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, new UserDaoImpl().getUserID(user.getUsername()));
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getUsername());
            pst.setString(4, message);
            pst.executeUpdate();
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserts data obtained from book request form to database.
     *
     * @param book
     */
    public void addBookRequest(Books book) {
        sqlQuery = "insert into tbl_bookRequest values(?,?,?, ?)";
        try {
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, book.getBookTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getGenre());
            pst.setBoolean(4, false);
            pst.executeUpdate();
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
