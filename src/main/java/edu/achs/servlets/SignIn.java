/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.dao.UserDao;
import edu.achs.daoImpl.UserDaoImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aashish Katwal
 */
@WebServlet(name = "SignIn", urlPatterns = {"/validateLogin"})
public class SignIn extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDao ud = new UserDaoImpl();
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            /**
             * Checks if a user exists with provided username and password. If
             * the user with the given credentials, information about him/her is
             * stored in the session and login is approved. Else, invalid login
             * message is sent.
             */
            if (ud.isValidUser(username, password)) {
                session.setAttribute("currentUser", ud.getLoggedinUser(username));
                request.getSession().setAttribute("successMsg", "Logged in successfully!!");
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.getSession().setAttribute("errorMsg", "Invalid Credentails. Please try again!!");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            request.getSession().setAttribute("errorMsg", "Invalid values. Please try again!!");
            response.sendRedirect(request.getContextPath() + "/login");
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
