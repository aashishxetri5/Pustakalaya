/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.daoImpl;

import edu.achs.dao.UserDao;
import edu.achs.entities.Users;
import edu.achs.utility.DBConnection;
import edu.achs.utility.PasswordHashing;
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
public class UserDaoImpl implements UserDao {

    String sqlQuery = "";
    boolean isUnique;

    @Override
    public void addUser(Users user) {
        try {
            sqlQuery = "insert into tbl_userdetails values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, user.getUserId());
            pst.setString(2, user.getLibraryId());
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getLastName());
            pst.setString(5, user.getGender());
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getAddress());
            pst.setString(8, user.getContactNum());
            pst.setDouble(9, user.getFine());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        addLoginCredentials(user.getUserId(), user.getUsername(), user.getPassword(), user.getUserType());
    }

    @Override
    public void addLoginCredentials(int userId, String username, String password, String userType) {
        try {
            sqlQuery = "insert into tbl_userlogindetails values (?,?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, username);
            pst.setString(3, new PasswordHashing().hashPassword(password));
            pst.setString(4, userType);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Users> getAllMembers(String userType) {
        List<Users> allUsers = new ArrayList<>();
        try {
            sqlQuery = "select tbl_userdetails.userId, tbl_userdetails.libraryId, tbl_userdetails.first_name, tbl_userdetails.last_name, "
                    + "tbl_userdetails.gender, tbl_userdetails.email, tbl_userdetails.address, tbl_userdetails.contactNum, "
                    + "tbl_userdetails.fine from tbl_userdetails inner join tbl_userlogindetails on "
                    + "tbl_userdetails.userId = tbl_userlogindetails.userId and tbl_userlogindetails.userType = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, userType);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("userId"));
                user.setLibraryId(rs.getString("libraryId"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setGender(rs.getString("gender"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setContactNum(rs.getString("contactNum"));
                user.setFine(rs.getDouble("fine"));
                allUsers.add(user);
            }
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    @Override
    public Users getLoggedinUser(String uname) {
        Users currentUser = new Users();
        try {
            sqlQuery = "select * from tbl_userlogindetails where username = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, uname);
            ResultSet rs = pst.executeQuery();
            rs.next();
            currentUser.setUserId(rs.getInt("userId"));
            currentUser.setUsername(rs.getString("username"));
            currentUser.setUserType(rs.getString("userType"));
            new DBConnection().getConnection().close();
        } catch (SQLException e) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return currentUser;
    }

    @Override
    public void updateUser(Users user) {
        try {
            sqlQuery = "update tbl_userdetails set first_name = ?, last_name = ?, email = ?, address = ?, contactNum = ?,"
                    + "gender = ? where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getAddress());
            pst.setString(5, user.getContactNum());
            pst.setString(6, user.getGender());
            pst.setInt(7, user.getUserId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValidUser(String username, String password) {
        PasswordHashing pwdHash = new PasswordHashing();
        boolean isAuthorizedUser = false;
        try {
            sqlQuery = "select username, password from tbl_userlogindetails";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("username").equals(username) && rs.getString("password").equals(pwdHash.hashPassword(password))) {
                    isAuthorizedUser = true;
                    break;
                }
            }
            new DBConnection().getConnection().close();
        } catch (SQLException e) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return isAuthorizedUser;
    }

    @Override
    public Users getCurrentUserDetail(int id) {
        Users user = new Users();
        try {
            sqlQuery = "select * from tbl_userdetails where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            user.setUserId(rs.getInt("userId"));
            user.setLibraryId(rs.getString("libraryId"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setAddress(rs.getString("address"));
            user.setContactNum(rs.getString("contactNum"));
            user.setFine(rs.getDouble("fine"));
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public int getUserID(String username) {
        int id = 0;
        try {
            sqlQuery = "select userId from tbl_userlogindetails where username = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt("userId");
            }
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public boolean isUniqueUsername(String username) {
        try {
            sqlQuery = "select username from tbl_userlogindetails where username = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            isUnique = checkUniqueness(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUnique;
    }

    @Override
    public boolean isDuplicateUserID(int generatedID) {

        try {
            sqlQuery = "select userId from tbl_userdetails where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, generatedID);
            ResultSet rs = pst.executeQuery();
            isUnique = checkUniqueness(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUnique;
    }

    @Override
    public boolean isDuplicateLibraryID(String generatedID) {
        try {
            sqlQuery = "select libraryId from tbl_userdetails where libraryId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, generatedID);
            ResultSet rs = pst.executeQuery();
            isUnique = checkUniqueness(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUnique;
    }

    public boolean checkUniqueness(ResultSet rs) throws SQLException {
        /**
         * Returns true is the result set is empty and false not empty. If data
         * is found, the result set would have some data. If data is not found,
         * the result set would be empty.
         */
        while (rs.next()) {
            return false;
        }
        return true;
    }

    public String getUserGender(int userId) {
        /**
         * Returns gender from the database using userId of currently logged in
         * user.
         */
        try {
            sqlQuery = "select gender from tbl_userdetails where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getString("gender");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "null";
    }
}
