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
                book.setISBN(rs.getString("isbn"));
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
        try {
            sqlQuery = "update tbl_books set title = ?, genre = ?, author = ?, publisher = ?, language = ?, ISBN = ?,"
                    + "edition = ?, stock = ?, price = ?, pages = ? where bookId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, book.getBookTitle());
            pst.setString(2, book.getGenre());
            pst.setString(3, book.getAuthor());
            pst.setString(4, book.getPublisher());
            pst.setString(5, book.getLanguage());
            pst.setString(6, book.getISBN());
            pst.setString(7, book.getEdition());
            pst.setInt(8, book.getStock());
            pst.setDouble(9, book.getPrice());
            pst.setString(10, book.getNumOfPages());
            pst.setString(11, book.getBookId());
            //This inserts the data received above to the respective fields in the table.
            pst.executeUpdate();
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteBook(String bookId) {
        try {
            sqlQuery = "delete from tbl_books where bookId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, bookId);
            pst.executeUpdate();
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Books> getSearchedBookDetail(String bookTitle) {
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

    @Override
    public Books getDetailsOfBookToBeUpdated(String bookId, String ISBN) {
        Books thisBook = new Books();
        try {
            sqlQuery = "select * from tbl_books where bookId = ? and ISBN = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, bookId);
            pst.setString(2, ISBN);
            ResultSet rs = pst.executeQuery();
            rs.next();
            thisBook.setBookId(rs.getString("bookId"));
            thisBook.setBookTitle(rs.getString("title"));
            thisBook.setAuthor(rs.getString("author"));
            thisBook.setPublisher(rs.getString("publisher"));
            thisBook.setEdition(rs.getString("edition"));
            thisBook.setNumOfPages(rs.getString("pages"));
            thisBook.setISBN(rs.getString("ISBN"));
            thisBook.setGenre(rs.getString("Genre"));
            thisBook.setStock(rs.getInt("stock"));
            thisBook.setPrice(rs.getDouble("price"));
            thisBook.setLanguage(rs.getString("language"));
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thisBook;
    }

    public boolean isBookBorrowed(String bookId) {
        try {
            sqlQuery = "select count(*) from tbl_borrow where bookId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, bookId);
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

    public boolean doesBookidAndIsbnExist(String bookId, String ISBN) {
        try {
            sqlQuery = "select count(*) from tbl_books where bookId = ? or ISBn = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, bookId);
            pst.setString(2, ISBN);
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
}
