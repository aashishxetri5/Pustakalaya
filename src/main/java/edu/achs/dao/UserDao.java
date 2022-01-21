/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.dao;

import edu.achs.entities.Users;
import java.util.List;

/**
 *
 * @author Aashish Katwal
 */
public interface UserDao {

    public void addUser(Users user);

    public void addLoginCredentials(int userId, String username, String password, String userType);

    public List<Users> getAllMembers(String userType);
    
//    public List<Users> getAllLibrarians();

    public Users getLoggedinUser(String username);

    public int getUserID(String username);

    public void updateUser(Users user);

    public void deleteUser(int id);

    public boolean isValidUser(String username, String password);

    public boolean isUniqueUsername(String username);

    public Users getCurrentUserDetail(int id);

    public boolean isDuplicateUserID(int generatedId);

    public boolean isDuplicateLibraryID(String generatedId);
}
