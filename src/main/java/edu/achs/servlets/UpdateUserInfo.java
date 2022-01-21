/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.daoImpl.UserDaoImpl;
import edu.achs.entities.Users;
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
@WebServlet(name = "UpdateUserInfo", urlPatterns = {"/updateInformation"})
public class UpdateUserInfo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDaoImpl udl = new UserDaoImpl();

        int userId = Integer.parseInt(request.getParameter("userId"));
        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phNum = request.getParameter("phoneNum");
        String gender = request.getParameter("gender");

        if (firstname != null && lastname != null && email != null && address != null && phNum != null && gender != null) {

            //checking if there has been some descripancies in the values of the field gender 
            if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("Other")) {

                //Assigns M for male, F for female, O for other.
                gender = (gender.equalsIgnoreCase("Male")) ? "M" : gender.equalsIgnoreCase("Female") ? "F" : "O";
                
                ///Sends the received data to updateUser() function.
                udl.updateUser(new Users(userId, firstname, lastname, gender, address, email, phNum));
                        
                request.setAttribute("successMsg", "Update successful!!");
            } else {
                request.setAttribute("errorMsg", "Your attempt to alter default values were detected. Please try again!!");
            }
            request.setAttribute("errorMsg", "Problem updating information. Please try again!!");
        }
        response.sendRedirect(request.getContextPath() + "/dashboard/profile");
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