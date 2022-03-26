/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.dashboardContents;

import edu.achs.entities.Books;
import edu.achs.utility.DBConnection;
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
public class PopularBooks extends DBConnection {

    /**
     * Fetches the popular books from the DB on the basis of borrow count.
     *
     * @param numberOfRecords
     * @return
     */
    public List<Books> getPopularBooks(int numberOfRecords) {

        List<Books> popularBooks = new ArrayList<>();

        try {
            String sqlQuery = "select tbl_books.title, tbl_books.author, tbl_books.publisher, tbl_books.edition, "
                    + "tbl_books.genre, tbl_books.language, tbl_borrowcount.borrow_times from tbl_books inner join "
                    + "tbl_borrowcount where tbl_books.bookId = tbl_borrowcount.bookId and tbl_borrowcount.borrow_times > 0 "
                    + "order by tbl_borrowcount.borrow_times desc limit ?";
            PreparedStatement pst = getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, numberOfRecords);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setEdition(rs.getString("edition"));
                book.setGenre(rs.getString("genre"));
                book.setLanguage(rs.getString("language"));
                book.setNumOfTimesBorrowed(rs.getInt("borrow_times"));
                popularBooks.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PopularBooks.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PopularBooks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return popularBooks;
    }
}
