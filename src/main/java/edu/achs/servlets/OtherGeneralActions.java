/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.daoImpl.UserDaoImpl;
import edu.achs.entities.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aashish Katwal
 */
@WebServlet(name = "OtherGeneralActions", urlPatterns = {"/user/changerole"})
public class OtherGeneralActions extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("currentUser") != null) {

            Users user = (Users) request.getSession().getAttribute("currentUser");

            if (user.getUserType().equals("Librarian")) {
                if (request.getRequestURI().contains("/user/changerole")) {
                    String userType = request.getParameter("currentRole");
                    int userId = Integer.parseInt(request.getParameter("userId"));

                    if ((userType.equals("Librarian") || userType.equals("Student")) && (userId >= 200000 && userId <= 300000)) {
                        String newRole = "", attrMsg = "";
                        if (userType.equals("Student")) {
                            newRole = "Librarian";
                            attrMsg = "User Promoted Successfully!!";
                        } else if (userType.equals("Librarian")) {
                            newRole = "Student";
                            attrMsg = "User Demoted Successfully!!";
                        }
                        new UserDaoImpl().changeRole(userId, newRole);
                        request.setAttribute("successMsg", attrMsg);
                    } else {
                        request.setAttribute("errorMsg", "Problem updating role. Please try again later!!");
                    }
                    response.sendRedirect(request.getContextPath() + "/dashboard/members/all");
                }
            } else {
                request.setAttribute("errorMsg", "Invalid Request!");
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            request.setAttribute("errorMsg", "Invalid Request!");
            response.sendRedirect(request.getContextPath() + "/home");
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
