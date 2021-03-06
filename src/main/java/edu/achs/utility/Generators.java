/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.utility;

import java.util.Random;

/**
 *
 * @author Aashish Katwal
 */
public class Generators {

    Random randNum = new Random();

    /**
     * Generates a random number between 200,000 and 300,000 which will be the
     * Id of user.
     *
     * @return
     */
    public int generateUserId() {
        return (randNum.nextInt(300000 - 200000) + 200000);
    }

    /**
     * Generates a random number between 200,000 and 300,000. This generated Id
     * will be converted to a string and concatenated to 'PST_' so the format of
     * the generated Id is 'PST_xxxxxx'
     *
     * @return
     */
    public String generateLibraryId() {
        String libId = "PST_" + Integer.toString((randNum.nextInt(600000 - 500000) + 500000));
        return libId;
    }

    public char[] generateSALT() {
        String saltSymbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%&*";
        char[] SALT = new char[15];
        for (int i = 0; i < 15; i++) {
            SALT[i] = saltSymbols.charAt(randNum.nextInt(saltSymbols.length()));
        }
        return SALT;
    }
}
