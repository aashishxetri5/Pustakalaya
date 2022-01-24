/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.daoImpl.OtherServices;
import edu.achs.entities.FeedbacksAndContacts;
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
@WebServlet(name = "FeedbackAndContact", urlPatterns = {"/FeedbackSubmit", "/ContactSubmit"})
public class FeedbackAndContact extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String message = "", email;

        OtherServices survey = new OtherServices();

        if (request.getParameter("ContactFormSubmission") != null) {
            String fullname = request.getParameter("fullname");
            email = request.getParameter("c-email");
            message = request.getParameter("c-message");

            //checking if any of the values are null
            if (fullname != null && email != null && message != null) {
                survey.insertContactInfo(new FeedbacksAndContacts(fullname, email, message));
                request.setAttribute("successMsg", "Contact request sent successfully!!");
            } else {
                request.setAttribute("errorMsg", "Could not place the request. Please try again!!");
            }

        } else if (request.getParameter("FeedbackFormSubmission") != null) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            email = request.getParameter("f-email");
            String username = request.getParameter("username");
            message = request.getParameter("f-message");

            //checking if any of the values are null
            if (email != null && username != null && message != null) {
                survey.insertFeedbacks(new FeedbacksAndContacts(userId, email, username, message));
                request.setAttribute("successMsg", "Feedback sent successfully!!");
            } else {
                request.setAttribute("errorMsg", "Could not send the feedback. Please try again!!");
            }

        }

        response.sendRedirect(request.getContextPath() + "/home");

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
