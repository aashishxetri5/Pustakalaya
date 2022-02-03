/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.utility;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Aashish Katwal
 */
public class GenerateDates {

    /**
     * Returns Date of the time this function was called. It converts LocalDate
     * to SQL date.
     *
     * @return
     */
    public Date getIssuedDate() {
        return Date.valueOf(LocalDate.now());
    }

    /**
     * Returns Date from 10 days of the time this function was called. It
     * converts LocalDate to SQL date.
     *
     * @return
     */
    public Date getReturnDate() {
        return Date.valueOf(LocalDate.now().plusDays(10));
    }
}
