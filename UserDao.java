/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public interface UserDao {
    public ArrayList<User> getUserList();
    public boolean updateUserStatus(String userId);
    public List<User> getTeachers();
    public User getUserDetailsByEmailId(String user_name, String user_type);
    public String getTeacherName(int teacherID);
    
}
