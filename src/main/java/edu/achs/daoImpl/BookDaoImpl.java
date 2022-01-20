/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.daoImpl;

import edu.achs.dao.BookDao;
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
public class BookDaoImpl implements BookDao {

    String sqlQuery = "";
    List<Books> allBooks = new ArrayList<Books>();

    @Override
    public void addBook(Books book) {
        try {
            sqlQuery = "insert into tbl_books values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, book.getBookId());
            pst.setString(2, book.getBookTitle());
            pst.setString(3, book.getGenre());
            pst.setString(4, book.getAuthor());
            pst.setString(5, book.getPublisher());
            pst.setString(6, book.getLanguage());
            pst.setString(7, book.getISBN());
            pst.setString(8, book.getEdition());
            pst.setInt(9, book.getStock());
            pst.setDouble(10, book.getPrice());
            pst.setString(11, book.getNumOfPages());
            //This inserts the data received above to the respective fields in the table.
            pst.executeUpdate();
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Books> getAllBooks() {
        try {
            sqlQuery = "select * from tbl_books";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            //loops through the table until there's a next record available and feteches it and stores into book.
            while (rs.next()) {
                Books book = new Books();
                book.setBookId(rs.getString("bookId"));
                book.setBookTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setGenre(rs.getString("genre"));
                book.setLanguage(rs.getString("language"));
                book.setPrice(rs.getDouble("price"));
                allBooks.add(book);
            }
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    @Override
    public List<Books> getBorrowedBooks(int userId) {
        try {
            sqlQuery = "select tbl_books.bookId, tbl_books.title, tbl_books.author, tbl_books.publisher, tbl_books.genre, "
                    + "tbl_borrow.issue_date, tbl_borrow.return_date, tbl_borrow.status from tbl_books inner join tbl_borrow on userId = ? "
                    + "and tbl_books.bookId = tbl_borrow.bookId;";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookId(rs.getString("bookId"));
                book.setBookTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setGenre(rs.getString("genre"));
                book.setIssue_date(rs.getString("issue_date"));
                book.setReturn_date(rs.getString("return_date"));
                book.setStatus(rs.getString("status"));
                allBooks.add(book);
            }
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    @Override
    public List<Books> getAllBorrowedBooks() {
        try {
            sqlQuery = "select tbl_books.bookId, tbl_books.title, tbl_books.author, tbl_books.publisher, tbl_books.genre, "
                    + "tbl_borrow.issue_date, tbl_borrow.return_date, tbl_borrow.status from tbl_books inner join tbl_borrow on tbl_books.bookId "
                    + "= tbl_borrow.bookId";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookId(rs.getString("bookId"));
                book.setBookTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setGenre(rs.getString("genre"));
                book.setIssue_date(rs.getString("issue_date"));
                book.setReturn_date(rs.getString("return_date"));
                book.setStatus(rs.getString("status"));
                allBooks.add(book);
            }
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    @Override
    public void updateBook(Books book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBook(int bookId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Books> getSearchedBookDetail(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getAllGenres() {
        ArrayList<String> allGenres = new ArrayList<>();
        try {
            sqlQuery = "select distinct(genre) from tbl_books";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("GENRE::: " + rs.getString("genre"));
                allGenres.add(rs.getString("genre"));
            }
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allGenres;
    }

}
