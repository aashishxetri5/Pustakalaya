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

    /**
     *
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public String getFullname() {
        return fullname;
    }

    /**
     *
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
