/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.entities;

/**
 *
 * @author Aashish Katwal
 */
public class Books {

//  private int numOfTimesBorrowed;
    
    private int stock, borrowerId;
    private String bookId, numOfPages, author, bookTitle, publisher, ISBN, edition, genre, language,
            issue_date, return_date, status;
    private double price;
    
    public Books() {
    }

    /**
     *
     * @param bookId
     * @param stock
     * @param numOfPages
     * @param author
     * @param bookTitle
     * @param publisher
     * @param ISBN
     * @param edition
     * @param genre
     * @param language
     * @param price
     */
    public Books(String bookId, int stock, String numOfPages, String author, String bookTitle, String publisher,
            String ISBN, String edition, String genre, String language, double price) {
        this.bookId = bookId;
        this.stock = stock;
        this.numOfPages = numOfPages;
        this.author = author;
        this.bookTitle = bookTitle;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.edition = edition;
        this.genre = genre;
        this.language = language;
        this.price = price;
    }

    /**
     *
     * @param author
     * @param bookTitle
     * @param genre
     * @param status
     */
    public Books(String author, String bookTitle, String genre, String status) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.status = status;
    }

    /**
     *
     * @param author
     * @param bookTitle
     * @param genre
     */
    public Books(String author, String bookTitle, String genre) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.genre = genre;
    }

    /**
     *
     * @return
     */
    public int getBorrowerId() {
        return borrowerId;
    }

    /**
     *
     * @param borrowerId
     */
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    /**
     *
     * @return
     */
    public String getBookId() {
        return bookId;
    }

    /**
     *
     * @param bookId
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     *
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

//    public int getNumOfTimesBorrowed() {
//        return numOfTimesBorrowed;
//    }
//
//    public void setNumOfTimesBorrowed(int numOfTimesBorrowed) {
//        this.numOfTimesBorrowed = numOfTimesBorrowed;
//    }
    /**
     *
     * @return
     */
    public String getNumOfPages() {
        return numOfPages;
    }

    /**
     *
     * @param numOfPages
     */
    public void setNumOfPages(String numOfPages) {
        this.numOfPages = numOfPages;
    }

    /**
     *
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     *
     * @param bookTitle
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     *
     * @return
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     *
     * @param publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     *
     * @return
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     *
     * @param ISBN
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     *
     * @return
     */
    public String getEdition() {
        return edition;
    }

    /**
     *
     * @param edition
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     *
     * @return
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     *
     * @return
     */
    public String getIssue_date() {
        return issue_date;
    }

    /**
     *
     * @param issue_date
     */
    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    /**
     *
     * @return
     */
    public String getReturn_date() {
        return return_date;
    }

    /**
     *
     * @param return_date
     */
    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
