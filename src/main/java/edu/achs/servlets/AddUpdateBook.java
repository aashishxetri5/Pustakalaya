/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.daoImpl.BookDaoImpl;
import edu.achs.entities.Books;
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
@WebServlet(name = "AddUpdateBook", urlPatterns = {"/addBook", "/book/updateBook"})
public class AddUpdateBook extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookId = request.getParameter("bookId");
        String title = request.getParameter("bookname");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String edition = request.getParameter("edition");
        String numOfPages = request.getParameter("numOfPages");
        String ISBN = request.getParameter("isbn");
        String genre = request.getParameter("genre");
        int stock = Integer.parseInt(request.getParameter("stock"));
        Double price = Double.parseDouble(request.getParameter("price"));
        String language = request.getParameter("language");

        if (bookId != null && title != null && author != null && publisher != null && edition != null
                && (numOfPages != "0" && numOfPages != null) && ISBN != null && genre != null && stock != 0 && price != 0.0
                && language != null) {

            BookDaoImpl bdi = new BookDaoImpl();
            //Checking if new Book is to be added or existing book is to be updated.
            if (request.getRequestURI().contains("/addBook")) {

                if (!bdi.doesBookidAndIsbnExist(bookId, ISBN)) {

                    bdi.addBook(new Books(bookId, stock, numOfPages, author, title, publisher, ISBN, edition, genre, language, price));
                    request.setAttribute("successMsg", "New book added successfully!!");
                } else {
                    request.setAttribute("errorMsg", "Duplicate Data. Try again!!");
                }

            } else if (request.getRequestURI().contains("/updateBook")) {
                bdi.updateBook(new Books(bookId, stock, numOfPages, author, title, publisher, ISBN, edition, genre, language, price));
                System.out.println("REACHED");
                request.setAttribute("successMsg", "Book updated successfully!!");
            }
            response.sendRedirect(request.getContextPath() + "/dashboard/books/all");
        } else {
            request.setAttribute("errorMsg", "Operation Failed. Please try again!!");
            if (request.getRequestURI().contains("/updateBook")) {
                response.sendRedirect(request.getContextPath() + "/dashboard/books/all");
            } else {
                response.sendRedirect(request.getContextPath() + "/newbook");
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
