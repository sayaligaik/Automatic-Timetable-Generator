/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.constant.Constant;
import com.dao.LabDao;
import com.database.Database;
import com.model.Lab;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public class LabDaoImpl implements LabDao, Constant {

    @Override
    public boolean insertLab(Lab lab) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLab(String labName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Lab> getAllLab() {
        List<Lab> labs = new ArrayList();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_LAB;
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
              Lab lab = new Lab();
              lab.setLabId(result.getInt(LAB_ID));
              lab.setLabName(result.getString(LAB_NAME));
              lab.setLabAddDate(result.getString(LAB_ADD_DATE));
              lab.setStatus(result.getString(STATUS));
              labs.add(lab);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return labs;
    }

    @Override
    public String getLabName(int labId) {
        String labName = "";
        try {
           String sql = "SELECT * FROM "+TABLE_NAME_LAB+" WHERE "+LAB_ID+"=?";
           PreparedStatement statement = Database.getConnction().prepareStatement(sql);
           statement.setInt(1, labId);
           ResultSet result = statement.executeQuery();
           while(result.next()){
               labName = result.getString(LAB_NAME);
           }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return labName;
    }

}
