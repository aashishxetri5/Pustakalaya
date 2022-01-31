/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.dao;

import edu.achs.entities.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aashish Katwal
 */
public interface UserDao {

    public void addUser(Users user);

    public void addLoginCredentials(int userId, String username, String password, String userType);

    public void saveProfilePicture(int userId, String imageFileName);

    public List<Users> getAllMembers(String userType);

    public Users getLoggedinUser(String username);

    public void updateUser(Users user);

    public void updateProfilePicture(int userId, String imageFileName);

    public void banUser(int userId);

    public boolean isValidUser(String username, String password);

    public boolean isUniqueUsername(String username);

    public Users getCurrentUserDetail(int userId);

    public int getUserID(String username);

    public boolean isDuplicateUserID(int generatedId);

    public boolean isDuplicateLibraryID(String generatedId);

    public boolean checkUniqueness(ResultSet rs) throws SQLException;

    public String getProfileImgName(int userId);
    
    public void changeRole(int userId, String newRole);
}
