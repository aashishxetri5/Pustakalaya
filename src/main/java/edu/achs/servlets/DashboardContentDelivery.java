/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.dao.BookDao;
import edu.achs.daoImpl.BookDaoImpl;
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
@WebServlet(name = "DashboardContentDelivery", urlPatterns = {"/dashboard/seachBook", "/requestedBook/markAvailable",
    "/dashboard/newGenre", "/genre/delete", "/notice/newNotice", "/notice/delete"})
public class DashboardContentDelivery extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookDao bd = new BookDaoImpl();
        if (request.getRequestURI().contains("/newGenre") && request.getParameter("addNewGenre") != null) {
            String genre = request.getParameter("genreTitle");
            if (!bd.doesGenreExist(genre)) {
                bd.addNewGenre(genre);
                request.getSession().setAttribute("successMsg", "Genre added successfully!!");
            } else {
                request.getSession().setAttribute("successMsg", "Operation failed. Genre already exists!");
            }
            response.sendRedirect(request.getContextPath() + "/dashboard/genres");
        } else if (request.getRequestURI().contains("/genre/delete")) {
            int genreId = Integer.parseInt(request.getParameter("genreId"));
            bd.removeGenre(genreId);
            request.getSession().setAttribute("successMsg", "Genre removed successfully!!");
            response.sendRedirect(request.getContextPath() + "/dashboard/genres");

        } else if (request.getRequestURI().contains("/requestedBook/markAvailable")) {
            int id = Integer.parseInt(request.getParameter("id"));
            new OtherServices().markAsAvailable(id);
            request.getSession().setAttribute("successMsg", "Marked available succesfully!!");
            response.sendRedirect((request.getContextPath() + "/dashboard/books/requests"));
        } else if (request.getRequestURI().contains("/notice/newNotice")) {
            String title = request.getParameter("notice-title");
//            String notice_to = request.getParameter("noticeFor");
            String message = request.getParameter("notice-message");
            new OtherServices().insertNotice(new FeedbacksAndContacts(title, message));
            request.getSession().setAttribute("successMsg", "Notice Uploaded Successfully!!");
            response.sendRedirect((request.getContextPath() + "/dashboard/notices"));
        } else if (request.getRequestURI().contains("/notice/delete")) {
            int id = Integer.parseInt(request.getParameter("noticeId"));
            new OtherServices().deleteNotice(id);
            request.getSession().setAttribute("successMsg", "Notice deleted successfully!!");
            response.sendRedirect(request.getContextPath() + "/dashboard/notices");
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
