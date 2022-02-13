/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.achs.daoImpl;

import edu.achs.dao.UserDao;
import edu.achs.entities.Users;
import edu.achs.utility.DBConnection;
import edu.achs.utility.Generators;
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

    /**
     * Adds the information of the user into the database.
     *
     * @param user
     */
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

    /**
     * Adds the login credentials into the database. The password is hashed with
     * the SHA-512 algorithm and saves it to the database. By default,
     * account_status is set to 'active'.
     *
     * @param userId
     * @param username
     * @param password
     * @param userType
     */
    @Override
    public void addLoginCredentials(int userId, String username, String password, String userType) {
        try {
            String SALT;
            while (true) {
                SALT = String.valueOf(new Generators().generateSALT());
                if (!doesSaltExist(String.valueOf(new Generators().generateSALT()))) {
                    break;
                }
            }
            sqlQuery = "insert into tbl_userlogindetails values (?,?,?,?,?,?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, username);
            pst.setString(3, new PasswordHashing().hashPassword(password.concat(SALT)));
            pst.setString(4, SALT);
            pst.setString(5, userType);
            pst.setString(6, "active");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Saves the Image name to the database along with the Id of the user with
     * whom the image is associated.
     *
     * @param userId
     * @param imageFileName
     */
    @Override
    public void saveProfilePicture(int userId, String imageFileName) {
        try {
            sqlQuery = "insert into tbl_userprofileimgs values (?, ?)";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            pst.setString(2, imageFileName);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updates the image filename to the new image's name.
     *
     * @param userId
     * @param imageFileName
     */
    @Override
    public void updateProfilePicture(int userId, String imageFileName) {
        try {
            sqlQuery = "update tbl_userprofileimgs set profileImgName = ? where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, imageFileName);
            pst.setInt(2, userId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fetches all the users depending on the type of user (Librarian, Member)
     *
     * @param userType
     * @return
     */
    @Override
    public List<Users> getAllMembers(String userType) {
        List<Users> allUsers = new ArrayList<>();
        try {
            sqlQuery = "select tbl_userdetails.userId, tbl_userdetails.libraryId, tbl_userdetails.first_name, "
                    + "tbl_userdetails.last_name, tbl_userdetails.gender, tbl_userdetails.email, tbl_userdetails.address, "
                    + "tbl_userdetails.contactNum, tbl_userdetails.fine from tbl_userdetails inner join "
                    + "tbl_userlogindetails on tbl_userdetails.userId = tbl_userlogindetails.userId and "
                    + "tbl_userlogindetails.userType = ?";
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
                user.setUserType(userType);
                allUsers.add(user);
            }
            new DBConnection().getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    /**
     * Fetches the details of currently logged in user on the basis of username
     *
     * @param uname
     * @return
     */
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

    /**
     * Updates the new details of the user.
     *
     * @param user
     */
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

    /**
     *
     *
     * @param userId
     */
    @Override
    public void banUser(int userId) {
        try {
            String deleteLoginDetails = "delete from tbl_userlogindetails where userId = ?";
            String deleteUserDetails = "delete from tbl_userdetails where userId = ?";
            String deleteProfileImg = "delete from tbl_userprofileimgs where userId = ?";
            PreparedStatement prepDltLoginCr = new DBConnection().getConnection().prepareStatement(deleteLoginDetails);
            PreparedStatement prepDltuserDetail = new DBConnection().getConnection().prepareStatement(deleteUserDetails);
            PreparedStatement prepDltProfileImg = new DBConnection().getConnection().prepareStatement(deleteProfileImg);

            prepDltLoginCr.setInt(1, userId);
            prepDltProfileImg.setInt(1, userId);
            prepDltuserDetail.setInt(1, userId);

            prepDltLoginCr.executeUpdate();
            prepDltProfileImg.executeUpdate();
            prepDltuserDetail.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Checks if the credentials entered by the user is valid or not.
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean isValidUser(String username, String password) {
        PasswordHashing pwdHash = new PasswordHashing();
        try {
            sqlQuery = "select count(*) from tbl_userlogindetails where username = ? and password = ? and account_status = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, username);
            pst.setString(2, pwdHash.hashPassword(password.concat(getSalt(username))));
            pst.setString(3, "active");
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.getInt(1) == 1) {
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    /**
     * Fetches the details of currently logged in user using the Id.
     *
     * @param userId
     * @return
     */
    @Override
    public Users getCurrentUserDetail(int userId) {
        Users user = new Users();
        try {
            sqlQuery = "select * from tbl_userdetails where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
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

    /**
     * Fetches userId based on the username.
     *
     * @param username
     * @return
     */
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

    /**
     * Checks if the generated SALT already exists
     *
     * @param SALT
     * @return
     */
    @Override
    public boolean doesSaltExist(String SALT) {
        try {
            sqlQuery = "select count(*) from tbl_userlogindetails where pSALT = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, SALT);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getSalt(String username) {
        try {
            sqlQuery = "select pSALT from tbl_userlogindetails where username = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getString("pSALT");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * Checks if the username is unique. This function is called before
     * registering the new user.
     *
     * @param username
     * @return
     */
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

    /**
     * Checks if the generated userId is already assigned to a user. This
     * function is called before registering a new user.
     *
     * @param generatedID
     * @return
     */
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

    /**
     * Checks if the generated libraryId is already assigned to a user. This
     * function is called before registering a new user.
     *
     * @param generatedID
     * @return
     */
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

    /**
     * Accepts the following parameter
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    @Override
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

    /**
     * Fetches the filename of the profile picture and returns it using which
     * the profile picture will be displayed.
     *
     * @param userId
     * @return
     */
    @Override
    public String getProfileImgName(int userId) {
        try {
            sqlQuery = "select profileImgName from tbl_userprofileimgs where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getString("profileImgName");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "null";
    }

    /**
     *
     * @param userId
     * @param userType
     */
    @Override
    public void changeRole(int userId, String newRole) {
        try {
            sqlQuery = "update tbl_userlogindetails set userType = ? where userId = ?";
            PreparedStatement pst = new DBConnection().getConnection().prepareStatement(sqlQuery);
            pst.setString(1, newRole);
            pst.setInt(2, userId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
