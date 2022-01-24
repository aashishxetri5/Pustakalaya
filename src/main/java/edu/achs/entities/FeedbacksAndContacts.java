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
public class FeedbacksAndContacts {

    private int userId;
    private String fullname, email, username, message;

    public FeedbacksAndContacts() {
    }

    /**
     * For Feedback form
     *
     * @param userId
     * @param email
     * @param username
     * @param message
     */
    public FeedbacksAndContacts(int userId, String email, String username, String message) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.message = message;
    }

    /**
     * For contact form
     *
     * @param fullname
     * @param email
     * @param message
     */
    public FeedbacksAndContacts(String fullname, String email, String message) {
        this.fullname = fullname;
        this.email = email;
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
