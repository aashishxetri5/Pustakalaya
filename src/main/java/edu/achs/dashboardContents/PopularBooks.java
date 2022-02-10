/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.dashboardContents;

import edu.achs.entities.Books;
import edu.achs.utility.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aashish Katwal
 */
public class PopularBooks {

    public List<Books> getPopularBooks() {
        
        List<Books> popularBooks = new ArrayList<>();
        
        try {
            String sqlQuery = "select * from tbl_borrowcount limit 6";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Books book = new Books();
//                book.getBookTitle(rs.getString(sqlQuery))

                popularBooks.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PopularBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return popularBooks;
    }
}
