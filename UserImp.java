/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.constant.Constant;
import com.dao.UserDao;
import com.database.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ganesh
 */
public class UserImp implements Constant, UserDao{
      
    @Override
    public ArrayList<User> getUserList(){
        ArrayList<User> resultList = new ArrayList<>();
          try {
            String sql = "SELECT * FROM "+TABLE_NAME_USER+" WHERE "+STATUS+"=? AND NOT "+
                         USER_TYPE+"=?";
              PreparedStatement statement = Database.getConnction().prepareStatement(sql);
              statement.setString(1, "0");
              statement.setString(2, "Incharge");
              ResultSet result = statement.executeQuery();
              while(result.next()){
                  User user = new User();
                  user.setUserId(result.getInt(USER_ID));
                  user.setUserName(result.getString(USER_NAME));
                  user.setUserEmailID(result.getString(USER_EMAIL_ID));
                  user.setUserType(result.getString(USER_TYPE));
                  user.setUserRegDate(result.getString(REGISTER_DATE));
                  resultList.add(user);
              }
        } catch (SQLException e) {
              Logger.getLogger("UserImpl.java").log(Level.WARNING,"Got Exception:",e.getMessage());
        }
       return resultList;
    }
    
    @Override
    public boolean updateUserStatus(String userId){
        boolean isValid = false;
        try {
            String sql = "UPDATE "+TABLE_NAME_USER+" SET "+STATUS+"=? WHERE "+
                         USER_ID+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, 1);
            statement.setString(2, userId);
            int result = statement.executeUpdate();
            if(result == 1){
                isValid = true;
            }
        } catch (SQLException e) {
            Logger.getLogger("UserImpl.java").log(Level.WARNING,"Got Exception:",e.getMessage());
        }
        return isValid;
    }
    
    @Override
    public List<User> getTeachers(){
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_USER+" WHERE "+USER_TYPE+" IN ('Teacher') AND "+
                        " "+STATUS+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, 1);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                User user = new User();
                user.setUserId(result.getInt(USER_ID));
                user.setUserEmailID(result.getString(USER_EMAIL_ID));
                user.setUserName(result.getString(USER_NAME));
                user.setUserType(result.getString(USER_TYPE));
                user.setUserRegDate(result.getString(REGISTER_DATE));
                users.add(user);
            }
        } catch (SQLException e) {
            Logger.getLogger("UserImpl.java : ").log(Level.INFO,"Got Exception:",e.getMessage());
        }
        return users;
    }
    
    @Override
    public User getUserDetailsByEmailId(String user_name, String user_type){
        User user = new User();
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_USER+" WHERE "+USER_EMAIL_ID+"=? "+
                    " AND "+USER_TYPE+"=? ";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setString(1, user_name);
            statement.setString(2, user_type);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                user.setUserId(result.getInt(USER_ID));
                user.setUserEmailID(result.getString(USER_EMAIL_ID));
                user.setUserName(result.getString(USER_NAME));
                user.setUserType(result.getString(USER_TYPE));
            }
                    
        } catch (SQLException e) {
            Logger.getLogger("UserImpl.java").log(Level.WARNING,"Got on exception : ",e.getMessage());
        }
        return user;
    }

    @Override
    public String getTeacherName(int teacherID) {
        String teacherName = "";
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_USER+" WHERE "+USER_ID+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, teacherID);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                teacherName = result.getString(USER_NAME);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return teacherName;
    }
}
