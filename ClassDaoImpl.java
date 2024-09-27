/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.constant.Constant;
import com.dao.ClassDao;
import com.database.Database;
import com.model.Class;
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
public class ClassDaoImpl implements Constant, ClassDao {

    @Override
    public boolean isClass(String className) {
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_CLASS
                    + " WHERE  " + CLASS_NAME + "=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setString(1, className);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (className.equals(result.getString(CLASS_NAME))) {
                    isValid = true;
                    break;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger("ClassRoomDaoImpl.java").log(Level.SEVERE, "Got on excepton", System.err);
        }
        return isValid;
    }

    @Override
    public boolean addClass(Class class_) {
        boolean isValid = false;
        try {
            String sql = "INSERT INTO "+TABLE_NAME_CLASS+" ( "+
                        CLASS_ID+", "+CLASS_NAME+","+CLASS_DATE+","+STATUS+") "+
                    " VALUES (?,?,?,?) ";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, class_.getClassId());
            statement.setString(2, class_.getClassName());
            statement.setString(3, class_.getClassAddDate());
            statement.setInt(4, class_.getStatus());
            int result = statement.executeUpdate();
            if(1 == result){
                isValid = true;
            }
        } catch (SQLException e) {
            Logger.getLogger("ClassRoomDaoImpl.java").log(Level.SEVERE, "Got on excepton", System.err);
        }
        return isValid;
    }

    @Override
    public List<Class> getAllClass() {
        List<Class> classes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_CLASS;
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Class class_ = new Class();
                class_.setClassId(result.getInt(CLASS_ID));
                class_.setClassName(result.getString(CLASS_NAME));
                class_.setClassAddDate(result.getString(CLASS_DATE));
                classes.add(class_);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return classes;
    }

    @Override
    public String getClassName(int classId) {
        String className = "";
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_CLASS+" WHERE "+CLASS_ID+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                if(classId == result.getInt(CLASS_ID)){
                    className = result.getString(CLASS_NAME);                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return className;
    }

}
