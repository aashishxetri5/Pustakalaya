/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.utility;

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
    public String getIssuedDate() {
        Date date = new Date();
        return ((1900 + date.getYear()) + "-" + (date.getMonth()+1) + "-" + (date.getDay()-1));
    }

    /**
     * Returns Date from 10 days of the time this function was called.
     *
     * @return
     */
    public String getReturnDate() {
        Date date = new Date();

        return ((1900 + date.getYear()) + "-" + (date.getMonth()+1) + "-" + (date.getDay() + 9));
    }
}
