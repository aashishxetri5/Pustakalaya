/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Aashish Katwal
 */
public class GenerateDates {

    /**
     * Returns Date of the time this function was called.
     *
     * @return
     */
    public String getCerrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/DD");
        System.out.println(formatter.format(date));
        return formatter.format(date);
    }
}
