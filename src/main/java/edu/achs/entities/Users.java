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
public class Users {

    private int userId;
    private String libraryId, firstName, lastName, gender, username, password, address, email, contactNum, userType;
    private double fine;

    public Users() {
    }

    /**
     *
     * @param libraryId
     * @param userId
     * @param firstName
     * @param lastName
     * @param gender
     * @param username
     * @param password
     * @param address
     * @param email
     * @param contactNum
     * @param userType
     * @param fine
     */
    public Users(String libraryId, int userId, String firstName, String lastName, String gender, String username, String password,
            String address, String email, String contactNum, String userType, double fine) {
        this.libraryId = libraryId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.contactNum = contactNum;
        this.userType = userType;
        this.fine = fine;
    }

    /**
     *
     * 
     * @param userId
     * @param firstName
     * @param lastName
     * @param gender
     * @param address
     * @param email
     * @param contactNum
     */
    public Users(int userId, String firstName, String lastName, String gender, String address, String email, String contactNum) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.contactNum = contactNum;
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public String getLibraryId() {
        return libraryId;
    }

    /**
     *
     * @param libraryId
     */
    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
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
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
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
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
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
    public String getContactNum() {
        return contactNum;
    }

    /**
     *
     * @param contactNum
     */
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    /**
     *
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     *
     * @return
     */
    public double getFine() {
        return fine;
    }

    /**
     *
     * @param fine
     */
    public void setFine(double fine) {
        this.fine = fine;
    }
}
