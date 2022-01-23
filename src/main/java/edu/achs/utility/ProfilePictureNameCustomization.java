/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aashish Katwal
 */
public class ProfilePictureNameCustomization {

    /**
     * Returns a custom name for an image file.
     *
     * @param userId
     * @param filename
     * @return
     */
    public String customizeProfilePictureName(int userId, String filename) {
        try {
            String sqlQuery = "select username from tbl_userlogindetails where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            /**
             * This gives the extension of the file. The filename is splitted by
             * '.' and the last word is joined with the filename which gives the
             * extension of the file.
             *
             */
            String extension = filename.split("\\.")[filename.split("\\.").length - 1];
            //This returns the custom filename with its extension.
            return ("PSTL_" + rs.getString("username") + "_" + Integer.toString(userId) + "." + extension);
        } catch (SQLException ex) {
            Logger.getLogger(ProfilePictureNameCustomization.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
