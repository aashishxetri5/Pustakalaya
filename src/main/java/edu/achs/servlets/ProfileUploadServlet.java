/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.servlets;

import edu.achs.daoImpl.UserDaoImpl;
import edu.achs.utility.ProfilePictureNameCustomization;
import edu.achs.utility.SaveProfileImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Aashish Katwal
 */
@WebServlet(name = "ProfileUploadServlet", urlPatterns = {"/dashboard/changePP"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1 // 1 MB
)
public class ProfileUploadServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));

        Part filePart = request.getPart("newProfileImg");

        /**
         * Checks if the file size is greater than 1MB. The profile is changed
         * only when the maximum limit is reached.
         *
         */
        if (filePart.getSize() <= (1024 * 1024)) {

            String filename = filePart.getSubmittedFileName();

            //This variable stores a custom generated name returned by this function.
            String newCustomFileName = new ProfilePictureNameCustomization().customizeProfilePictureName(userId, filename);

            //Path of TempPPs folder. This saves the uploaded file to TempPPs folder.
            String temppath = request.getRealPath("Images") + File.separator + "TempPPs" + File.separator + filename;

            /**
             * Path of ProfilePictures folder. This is where the uploaded file
             * will be moved to from TempPPs folder along with a custom image
             * name.
             */
            String realPath = request.getRealPath("Images") + File.separator + "ProfilePictures" + File.separator + newCustomFileName;

            //Checks if the current image name is that of a default img file name
            if (!new UserDaoImpl().getProfileImgName(userId).equalsIgnoreCase("Male_Default_pp.png")
                    && !new UserDaoImpl().getProfileImgName(userId).equalsIgnoreCase("Female_Default_pp.png") 
                    && !new UserDaoImpl().getProfileImgName(userId).equalsIgnoreCase("Others_Default_pp.png")) {

                System.out.println("REACHED HERE + " + new UserDaoImpl().getProfileImgName(userId));
                
                //Deletes the old file so that there will be room for the new img file.
                Files.delete(Paths.get(request.getRealPath("Images") + File.separator + "ProfilePictures" + File.separator
                        + new UserDaoImpl().getProfileImgName(userId)));
            }

            //Save profile picture to the tempPPs folder
            new SaveProfileImage().saveImage(temppath, filePart);

            //Move file from tempPP folder to ProfilePictures folder.
            Path path = Files.move(Paths.get(temppath), Paths.get(realPath));

            //update the profile picture name in database
            new UserDaoImpl().updateProfilePicture(userId, newCustomFileName);
            request.setAttribute("successMsg", "Profile changed successfully!!");
        } else {
            request.setAttribute("errorMsg", "Failed to change profile picture!!");
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
