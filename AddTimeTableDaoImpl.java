/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.constant.Constant;
import com.dao.AddTimeTableDao;
import com.database.Database;
import com.model.TimeTableDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */
public class AddTimeTableDaoImpl implements AddTimeTableDao, Constant {

    @Override
    public boolean insertTimeTablePracticalDetails(TimeTableDetails timeTableDetails) {
        boolean isValid = false;
        try {
            String sql = "INSERT INTO " + TABLE_NAME_TIME_TABLE_DETAILS + " ( "
                    + TIME_TABLE_ID + ","
                    + TIME_TABLE_TEACHER_ID + ","
                    + TIME_TABLE_SUBJECT_ID + ","
                    + TIME_TABLE_CLASS_ID + ","
                    + LAB_ID + ","
                    + TIME_TABLE_TYPE + ","
                    + TIME_TABLE_HRS + ","
                    + TIME_TABLE_ADD_DATE+","
                    + BATCH_ID + ") VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, timeTableDetails.getTimeTableID());
            statement.setInt(2, timeTableDetails.getTeacherID());
            statement.setInt(3, timeTableDetails.getSubjectID());
            statement.setInt(4, timeTableDetails.getClassID());
            statement.setInt(5, timeTableDetails.getLabId());
            statement.setString(6, timeTableDetails.getType());
            statement.setInt(7, timeTableDetails.getTimeHrs());
            statement.setString(8, timeTableDetails.getAddDate());
            statement.setInt(9, timeTableDetails.getBatchId());
            int result = statement.executeUpdate();
            if (1 == result) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

    @Override
    public boolean isTimeTablePracticalDetails(TimeTableDetails timeTableDetails) {
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_TIME_TABLE_DETAILS + " WHERE "
                    + TIME_TABLE_TEACHER_ID + "=? AND "
                    + TIME_TABLE_SUBJECT_ID + "=? AND "
                    + TIME_TABLE_CLASS_ID + "=? AND "
                    + LAB_ID + "=? AND "
                    + TIME_TABLE_TYPE + "=?  AND "
                    + BATCH_ID + "=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, timeTableDetails.getTeacherID());
            statement.setInt(2, timeTableDetails.getSubjectID());
            statement.setInt(3, timeTableDetails.getClassID());
            statement.setInt(4, timeTableDetails.getLabId());
            statement.setString(5, timeTableDetails.getType());
            statement.setInt(6, timeTableDetails.getBatchId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (result.getInt(TIME_TABLE_TEACHER_ID) == timeTableDetails.getTeacherID()
                        && result.getInt(TIME_TABLE_SUBJECT_ID) == timeTableDetails.getSubjectID()
                        && result.getInt(TIME_TABLE_CLASS_ID) == timeTableDetails.getClassID()
                        && result.getInt(LAB_ID) == timeTableDetails.getLabId()
                        && result.getString(TIME_TABLE_TYPE).equals(timeTableDetails.getType())
                        && result.getInt(BATCH_ID) == timeTableDetails.getBatchId()) {
                    isValid = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

    @Override
    public boolean insertTimeTableTheoryDetails(TimeTableDetails timeTableDetails) {
        boolean isValid = false;
        try {
            String sql = "INSERT INTO " + TABLE_NAME_TIME_TABLE_DETAILS + " ( "
                    + TIME_TABLE_ID + ","
                    + TIME_TABLE_TEACHER_ID + ","
                    + TIME_TABLE_SUBJECT_ID + ","
                    + TIME_TABLE_CLASS_ID + ","
                    + TIME_TABLE_CLASS_ROOM_ID + ","
                    + TIME_TABLE_TYPE + ","
                    + TIME_TABLE_HRS + ","
                    + TIME_TABLE_ADD_DATE+","
                    + BATCH_ID + ") VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, timeTableDetails.getTimeTableID());
            statement.setInt(2, timeTableDetails.getTeacherID());
            statement.setInt(3, timeTableDetails.getSubjectID());
            statement.setInt(4, timeTableDetails.getClassID());
            statement.setInt(5, timeTableDetails.getClassRoomID());
            statement.setString(6, timeTableDetails.getType());
            statement.setInt(7, timeTableDetails.getTimeHrs());
            statement.setString(8, timeTableDetails.getAddDate());
            statement.setInt(9, timeTableDetails.getBatchId());
            int result = statement.executeUpdate();
            if (1 == result) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

    @Override
    public boolean isTimeTableTheoryDetails(TimeTableDetails timeTableDetails) {
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_TIME_TABLE_DETAILS + " WHERE "
                    + TIME_TABLE_TEACHER_ID + "=? AND "
                    + TIME_TABLE_SUBJECT_ID + "=? AND "
                    + TIME_TABLE_CLASS_ID + "=? AND "
                    + TIME_TABLE_CLASS_ROOM_ID + "=? AND "
                    + TIME_TABLE_TYPE + "=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, timeTableDetails.getTeacherID());
            statement.setInt(2, timeTableDetails.getSubjectID());
            statement.setInt(3, timeTableDetails.getClassID());
            statement.setInt(4, timeTableDetails.getClassRoomID());
            statement.setString(5, timeTableDetails.getType());
//            statement.setInt(6, timeTableDetails.getBatchId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (result.getInt(TIME_TABLE_TEACHER_ID) == timeTableDetails.getTeacherID()
                        && result.getInt(TIME_TABLE_SUBJECT_ID) == timeTableDetails.getSubjectID()
                        && result.getInt(TIME_TABLE_CLASS_ID) == timeTableDetails.getClassID()
                        && result.getInt(TIME_TABLE_CLASS_ROOM_ID) == timeTableDetails.getClassRoomID()
                        && result.getString(TIME_TABLE_TYPE).equals(timeTableDetails.getType())
                        ) {
                    isValid = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

    @Override
    public List<TimeTableDetails> getClassTimeDetails(int classID) {
        List<TimeTableDetails> classTimeDetails = new ArrayList();
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_TIME_TABLE_DETAILS+" WHERE "+
                          TIME_TABLE_CLASS_ID+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classID);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                TimeTableDetails timeTableDetails = new TimeTableDetails();
                timeTableDetails.setTimeTableID(result.getInt(TIME_TABLE_ID));
                timeTableDetails.setTeacherID(result.getInt(TIME_TABLE_TEACHER_ID));
                timeTableDetails.setSubjectID(result.getInt(TIME_TABLE_SUBJECT_ID));
                timeTableDetails.setClassID(result.getInt(TIME_TABLE_CLASS_ID));
                timeTableDetails.setClassRoomID(result.getInt(TIME_TABLE_CLASS_ROOM_ID));
                timeTableDetails.setLabId(result.getInt(LAB_ID));
                timeTableDetails.setType(result.getString(TIME_TABLE_TYPE));
                timeTableDetails.setTimeHrs(result.getInt(TIME_TABLE_HRS));
                timeTableDetails.setBatchId(result.getInt(BATCH_ID));
                timeTableDetails.setAddDate(result.getString(TIME_TABLE_ADD_DATE));
                classTimeDetails.add(timeTableDetails);
            }
            
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return classTimeDetails;
    }

    @Override
    public boolean isValidClassTimeDetailsForPractical(int classId) {
        boolean isValid = false;
        try {
            String sql = "SELECT SUM("+TIME_TABLE_HRS+") AS "+TIME_TABLE_HRS+" FROM "+TABLE_NAME_TIME_TABLE_DETAILS
                         +" WHERE "+TIME_TABLE_CLASS_ID+"=? AND "+TIME_TABLE_TYPE+"=?";
//            System.out.println("SQL : "+sql);
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classId);
            statement.setString(2, "practical");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int sum = result.getInt(TIME_TABLE_HRS);
//                System.out.println("Sum : "+sum);
                if(sum <= 30){
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

    @Override
    public boolean isValidClassTimeDetailsForTheory(int classId) {
        boolean isValid = false;
        try {
            String sql = "SELECT SUM("+TIME_TABLE_HRS+") AS "+TIME_TABLE_HRS+" FROM "+TABLE_NAME_TIME_TABLE_DETAILS
                         +" WHERE "+TIME_TABLE_CLASS_ID+"=? AND "+TIME_TABLE_TYPE+"=?";
            System.out.println("SQL : "+sql);
            System.out.println("Class Id :: "+classId);
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classId);
            statement.setString(2, "theory");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int sum = result.getInt(TIME_TABLE_HRS);
//                System.out.println("Sum : "+sum);
                if(sum <= 20){
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

}
