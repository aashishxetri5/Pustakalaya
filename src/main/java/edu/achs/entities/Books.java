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

//    private int numOfTimesBorrowed;
    private int stock;
    private String bookId, numOfPages, author, bookTitle, publisher, ISBN, edition, genre, language,
            issue_date, return_date, status;
    private double price;

    public Books() {
    }

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

    public Books(String author, String bookTitle, String genre, String status) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.status = status;
    }

    public Books(String author, String bookTitle, String genre) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.genre = genre;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getStock() {
        return stock;
    }

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
    public String getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(String numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
