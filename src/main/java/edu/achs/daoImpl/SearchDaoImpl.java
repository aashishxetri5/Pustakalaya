/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.daoImpl;

import edu.achs.dao.SearchDao;
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
public class SearchDaoImpl implements SearchDao {

    private String sqlQuery = "";
    private final List<Books> bookSearchResults = new ArrayList<>();
    private PreparedStatement pst;

    /**
     * Returns the detail of the book of a specific author.
     *
     * @param authorName
     * @return
     */
    @Override
    public List<Books> getBooksByAuthor(String authorName) {
        try {
            sqlQuery = "select * from tbl_books where author like ? or ?";
            pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, "%".concat(authorName).concat("%"));
            pst.setString(2, authorName);
            getAllBooks(pst);

        } catch (SQLException ex) {
            Logger.getLogger(SearchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SearchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return bookSearchResults;
    }

    /**
     * Returns the detail of the book that is searched by name.
     *
     * @param bookName
     * @return
     */
    @Override
    public List<Books> getBooksByBookName(String bookName) {
        try {
            sqlQuery = "select * from tbl_books where title like ? or ?";
            pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, "%".concat(bookName).concat("%"));
            pst.setString(2, bookName);
            getAllBooks(pst);
        } catch (SQLException ex) {
            Logger.getLogger(SearchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(SearchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return bookSearchResults;
    }

    /**
     * fetches the books' detail from
     *
     * @param pst
     */
    public void getAllBooks(PreparedStatement pst) {
        try {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookId(rs.getString("bookId"));
                book.setBookTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getString("isbn"));
                book.setLanguage(rs.getString("language"));
                book.setPrice(rs.getDouble("price"));
                bookSearchResults.add(book);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
