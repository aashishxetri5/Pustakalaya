/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.utility;

import edu.achs.daoImpl.UserDaoImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author Aashish Katwal
 */
public class SaveProfileImage {

    /**
     * Accepts path where the file is to saved and the file part. This writes
     * the image to given destination.
     *
     * @param path
     * @param filePart
     */
    public void saveImage(String path, Part filePart) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            InputStream ips = filePart.getInputStream();

            byte[] data = new byte[ips.available()];
            ips.read(data);
            fos.write(data);
            ips.close();
            fos.close();
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(SaveProfileImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
