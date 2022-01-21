/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.daoImpl.UserDaoImpl;
import edu.achs.entities.Users;
import edu.achs.utility.GenerateIDs;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aashish Katwal
 */
@WebServlet(name = "Registration", urlPatterns = {"/validateRegistration"})
public class UserRegistration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenerateIDs generate = new GenerateIDs();
        UserDaoImpl udl = new UserDaoImpl();

        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phNum = request.getParameter("phoneNum");
        String gender = request.getParameter("gender");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //Checking if any of the above fields have received null values.
        if (firstname != null && lastname != null && email != null && address != null && phNum != null && gender != null
                && username != null && password != null) {
            
            //checking if the username is unique
            if (udl.isUniqueUsername(username)) {
                //checking if there has been some descripancies in the values of the field gender 
                if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("Other")) {
                    int userId;
                    String libraryId = "";

                    // Generate userId and check duplication
                    while (true) {
                        userId = generate.generateUserId();
                        if (udl.isDuplicateUserID(userId)) {
                            break;
                        }
                    }

                    //Generate libraryId and check duplication
                    while (true) {
                        libraryId = generate.generateLibraryId();
                        if (udl.isDuplicateLibraryID(libraryId)) {
                            break;
                        }
                    }
                    
                    //Assigns M for male, F for female, O for other.
                    gender = (gender.equalsIgnoreCase("Male")) ? "M" : gender.equalsIgnoreCase("Female") ? "F" : "O";
                    
                    ///Sends the received data to addUser() function.
                    udl.addUser(new Users(libraryId, userId, firstname, lastname, gender, username, password, address, email,
                            phNum, "Student", 0.0));

                    request.setAttribute("successMsg", "You're now successfully registered. Welcome to Pustakalaya!!");
                } else {
                    request.setAttribute("errorMsg", "Your attempt to alter default values were detected. Please try again!!");
                }
            } else {
                request.setAttribute("errorMsg", "The username already exists. Please use a different one!!");
            }
        } else {
            request.setAttribute("errorMsg", "There was a problem registering you. Please try again!!");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}