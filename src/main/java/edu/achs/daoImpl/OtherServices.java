/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.daoImpl;

import edu.achs.entities.Books;
import edu.achs.entities.FeedbacksAndContacts;
import edu.achs.utility.DBConnection;
import edu.achs.utility.GenerateDates;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aashish Katwal
 */
public class OtherServices {
    
    private String sqlQuery;

    /**
     * Inserts the data obtained from contact form and saves it in database
     *
     * @param fac
     */
    public void insertContactInfo(FeedbacksAndContacts fac) {
        try {
            sqlQuery = "insert into tbl_contact (fullname, email, message) values(?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, fac.getFullname());
            pst.setString(2, fac.getEmail());
            pst.setString(3, fac.getMessage());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserts the data obtained from feedback form and saves it in database.
     *
     * @param fac
     */
    public void insertFeedbacks(FeedbacksAndContacts fac) {
        try {
            sqlQuery = "insert into tbl_feedback values(?,?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, new UserDaoImpl().getUserID(fac.getUsername()));
            pst.setString(2, fac.getEmail());
            pst.setString(3, fac.getUsername());
            pst.setString(4, fac.getMessage());
            pst.executeUpdate();
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
        try {
            sqlQuery = "insert into tbl_bookRequest (book_name, author, genre, isListed) values(?,?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, book.getBookTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getGenre());
            pst.setBoolean(4, false);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves all the requested books from the DB.
     *
     * @return
     */
    public List<Books> getRequestedBooks() {
        List<Books> requestedBooks = new ArrayList<>();
        try {
            sqlQuery = "select * from tbl_bookRequest order by tbl_bookrequest.isListed asc";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookId(String.valueOf(rs.getInt("id")));
                book.setBookTitle(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setStatus(String.valueOf(rs.getBoolean("isListed")));
                requestedBooks.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requestedBooks;
    }

    /**
     *
     * @param id
     */
    public void markAsAvailable(int id) {
        try {
            sqlQuery = "update tbl_bookRequest set isListed = ? where id = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setBoolean(1, true);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fetches and returns all the feedbacks received
     *
     * @return
     */
    public List<FeedbacksAndContacts> getAllFeedbacks() {
        List<FeedbacksAndContacts> allFeedbacks = new ArrayList<>();
        try {
            sqlQuery = "select * from tbl_feedback";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                FeedbacksAndContacts feedback = new FeedbacksAndContacts();
                feedback.setEmail(rs.getString("email"));
                feedback.setUsername(rs.getString("username"));
                feedback.setMessage(rs.getString("f_message"));
                allFeedbacks.add(feedback);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allFeedbacks;
    }

    /**
     *
     * @param fac
     */
    public void insertNotice(FeedbacksAndContacts fac) {
        try {
            sqlQuery = "insert into tbl_notices (title, message, pub_date) values(?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, fac.getTitle());
            pst.setString(2, fac.getMessage());
            pst.setDate(3, new GenerateDates().getCurrentDate());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public List<FeedbacksAndContacts> getAllNotices() {
        List<FeedbacksAndContacts> allNotices = new ArrayList<>();
        try {
            sqlQuery = "select * from tbl_notices order by pub_date desc";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                FeedbacksAndContacts fac = new FeedbacksAndContacts();
                fac.setId(rs.getInt("id"));
                fac.setTitle(rs.getString("title"));
                fac.setMessage(rs.getString("message"));
                fac.setDate(rs.getDate("pub_date"));
                allNotices.add(fac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OtherServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allNotices;
    }
    
}
