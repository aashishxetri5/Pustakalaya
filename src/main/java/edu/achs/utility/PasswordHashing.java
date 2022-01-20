/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.utility;

import java.security.MessageDigest;

/**
 *
 * @author asus
 */
public class PasswordHashing {

    final protected char[] hexCharArray = "0123456789ABCDEF".toCharArray();

    private String SALT = "3nTeR3d";

    public String bytesToHexa(byte[] bytes) {
        char[] hexaCharacters = new char[bytes.length * 2];
        int v;
        for (int i = 0; i < bytes.length; i++) {
            v = bytes[i] & 0xFF;
            hexaCharacters[i * 2] = hexCharArray[v >>> 4];
            hexaCharacters[i * 2 + 1] = hexCharArray[v & 0x0F];
        }
        return new String(hexaCharacters);
    }

    public String hashPassword(String unhashedPwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(SALT.getBytes());
            md.update(unhashedPwd.getBytes());
            byte[] out = md.digest();
            return bytesToHexa(out);
        } catch (Exception e) {
        }
        return "";
    }
}
