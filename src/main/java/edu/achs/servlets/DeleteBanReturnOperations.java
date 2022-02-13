/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.daoImpl.BookDaoImpl;
import edu.achs.daoImpl.UserDaoImpl;
import edu.achs.entities.Users;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aashish Katwal
 */
@WebServlet(name = "DeleteOperations", urlPatterns = {"/book/deleteBook", "/user/ban"})
public class DeleteBanReturnOperations extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("currentUser") != null) {

            Users user = (Users) request.getSession().getAttribute("currentUser");

            if (user.getUserType().equals("Librarian")) {
                if (request.getRequestURI().contains("/deleteBook")) {
                    String bookId = request.getParameter("bookId").trim();
                    if (new BookDaoImpl().isBookBorrowed(bookId)) {
                        request.getSession().setAttribute("errorMsg", "The book is borrowed by member(s).");
                    } else {
                        new BookDaoImpl().deleteBook(bookId);
                    }
                    response.sendRedirect(request.getContextPath() + "/dashboard/books/all");

                } else if (request.getRequestURI().contains("/user/ban")) {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    if (!new BookDaoImpl().isBookBorrowedByUser(userId)) {
                        String profileImgName = new UserDaoImpl().getProfileImgName(userId);

                        new UserDaoImpl().banUser(userId);

                        if (!profileImgName.contains("_Default_pp.png")) {

                            Files.deleteIfExists(Paths.get(request.getRealPath("Images") + File.separator + "ProfilePictures"
                                    + File.separator + profileImgName));
                        }
                        request.getSession().setAttribute("successMsg", "User Removed Successfully!!");
                    } else {
                        request.getSession().setAttribute("errorMsg", "Operation failed. User hasn't returned books!!");
                    }
                    response.sendRedirect(request.getContextPath() + "/dashboard/members/all");
                }
            } else {
                request.getSession().setAttribute("errorMsg", "Invalid request");
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            request.getSession().setAttribute("errorMsg", "Invalid Request!");
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
