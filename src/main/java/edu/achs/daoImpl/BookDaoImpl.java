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
    List<Books> allBooks = new ArrayList<>();

    /**
     * Inserts information of a new book to the database
     *
     * @param book
     */
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

    /**
     * Fetches information of all the books from the database and returns it.
     *
     * @return
     */
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

    /**
     * Fetches all the books borrowed by a specific user. A user's id is used to
     * for this purpose. This function is for an individual member to see which
     * book(s) s/he has borrowed.
     *
     * @param userId
     * @return
     */
    @Override
    public List<Books> getBorrowedBooks(int userId) {
        try {
            sqlQuery = "select tbl_books.bookId, tbl_books.title, tbl_books.author, tbl_books.publisher, tbl_books.genre, "
                    + "tbl_borrow.issue_date, tbl_borrow.return_date, tbl_borrow.status from tbl_books inner join tbl_borrow "
                    + "on userId = ? and tbl_books.bookId = tbl_borrow.bookId order by tbl_borrow.status ASC";
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

    /**
     * Fetches all the books that has been borrowed along with other details and
     * Id of user who borrowed it. Inner join is used to fetch record.
     *
     * @return
     */
    @Override
    public List<Books> getAllBorrowedBooks() {
        try {
            sqlQuery = "select tbl_books.bookId, tbl_books.title, tbl_books.author, tbl_books.publisher, tbl_books.genre, "
                    + "tbl_borrow.userId, tbl_borrow.issue_date, tbl_borrow.return_date, tbl_borrow.status from tbl_books "
                    + "inner join tbl_borrow on tbl_books.bookId = tbl_borrow.bookId order by tbl_borrow.status ASC";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBorrowerId(rs.getInt("userId"));
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

    /**
     * Updates the Book with new information.
     *
     * @param book
     */
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

    /**
     * Deletes a book from the database. A book is identified using its Id.
     *
     * @param bookId
     */
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

    /**
     * Returns the detail of the book that is searched.
     *
     * @param bookTitle
     * @return
     */
    @Override
    public List<Books> getSearchedBookDetail(String bookTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Fetches all the genres from the database.
     *
     * @return
     */
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

    /**
     * Fetches the details of the book to be updated and returns it.
     *
     * @param bookId
     * @param ISBN
     * @return
     */
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
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thisBook;
    }

    /**
     * Fetches the first name and last name of a user using their userId and
     * returns the full name after concatenating them.
     *
     * @param userId
     * @return
     */
    @Override
    public String getBorrowerName(int userId) {
        String fullname = "";
        try {
            sqlQuery = "select first_name, last_name from tbl_userdetails where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            fullname = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fullname;
    }

    /**
     * Checks if the book is borrowed by any member currently. This function is
     * called before a delete book operation is carried out.
     *
     * @param bookId
     * @return
     */
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

    /**
     * Checks if the book already exists in the database. This function is
     * called each time a book is added to the database or updated.
     *
     * @param bookId
     * @param ISBN
     * @return
     */
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
    
    public boolean isBookBorrowedByUser(int userId) {
        try {
            sqlQuery = "select count(*) from tbl_borrow where userId = ? and status = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, "pending");
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
