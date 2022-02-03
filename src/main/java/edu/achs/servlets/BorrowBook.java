/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.dao.BookDao;
import edu.achs.dao.BorrowDao;
import edu.achs.daoImpl.BookDaoImpl;
import edu.achs.daoImpl.BorrowDaoImpl;
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
@WebServlet(name = "BorrowBook", urlPatterns = {"/book/borrow"})
public class BorrowBook extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("currentUser") != null) {
            String bookId = request.getParameter("bookId");
            int borrowerId = Integer.parseInt(request.getParameter("borrwerId"));

            BorrowDao bd = new BorrowDaoImpl();
            BookDaoImpl bdl = new BookDaoImpl();

            if (!bdl.isBookBorrowedByUser(borrowerId)) {
                if (bd.canUserBorrowBook(borrowerId)) {
                    if (bd.isBookInStock(bookId)) {
                        bd.borrowBookProcess(borrowerId, bookId);
                        request.setAttribute("successMsg", "Book borrowed successfully!");
                        response.sendRedirect(request.getContextPath() + "/dashboard/books/borrowed");
                    } else {
                        request.setAttribute("errorMsg", "Book out of stock");
                        response.sendRedirect(request.getContextPath() + "/dashboard/books/all");
                    }
                } else {
                    request.setAttribute("errorMsg", "You can not borrow more than 5 books!");
                    response.sendRedirect(request.getContextPath() + "/dashboard/books/all");
                }
            } else {
                request.setAttribute("errorMsg", "This Book already borrowed!");
                response.sendRedirect(request.getContextPath() + "/dashboard/books/all");
            }
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
