/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.constant.Constant;
import com.dao.TimeTableDao;
import com.database.Database;
import com.model.TimeTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public class TimeTableDaoImpl implements TimeTableDao, Constant {

    @Override
    public boolean insertTimeTable(TimeTable timeTable) {
        boolean isValid = false;
        try {
            String sql = "INSERT INTO " + TABLE_NAME_TIME_TABLE + " ( "
                    + TIME_TABLE_ID + ","
                    + TIME_TABLE_TEACHER_ID + ","
                    + TIME_TABLE_SUBJECT_ID + ","
                    + TIME_TABLE_CLASS_ID + ","
                    + TIME_TABLE_CLASS_ROOM_ID + ","
                    + LAB_ID + ","
                    + TIME_TABLE_TYPE + ","
                    + TIME_TABLE_HRS + ","
                    + TIME_TABLE_ADD_DATE + ","
                    + DAY_ID + ","
                    + TIME_ID + ","
                    + BATCH_ID + ","
                    + STATUS + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, timeTable.getTimeTableId());
            statement.setInt(2, timeTable.getTeacherId());
            statement.setInt(3, timeTable.getSubjectId());
            statement.setInt(4, timeTable.getClassId());
            statement.setInt(5, timeTable.getClassRoomId());
            statement.setInt(6, timeTable.getLabId());
            statement.setString(7, timeTable.getType());
            statement.setInt(8, timeTable.getHrs());
            statement.setString(9, timeTable.getAddDate());
            statement.setInt(10, timeTable.getDayId());
            statement.setInt(11, timeTable.getTimeId());
            statement.setInt(12, timeTable.getBatchId());
            statement.setInt(13, timeTable.getStatus());
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
    public boolean rejectTimeTable(int classId) {
        boolean isValid = false;
        try {
            String sql = "DELETE FROM " + TABLE_NAME_TIME_TABLE + " WHERE " + TIME_TABLE_CLASS_ID + "=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classId);
            int result = statement.executeUpdate();
            if (result >= 1) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

    @Override
    public List<TimeTable> getTimeTable(int classId, int dayId, int timeId) {
        List<TimeTable> timeTables = new ArrayList();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_TIME_TABLE + " WHERE " + TIME_TABLE_CLASS_ID + "=? AND "
                    + DAY_ID + "=? AND " + TIME_ID + "=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classId);
            statement.setInt(2, dayId);
            statement.setInt(3, timeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TimeTable timeTable = new TimeTable();
                timeTable.setTimeTableId(resultSet.getInt(TIME_TABLE_ID));
                timeTable.setTeacherId(resultSet.getInt(TIME_TABLE_TEACHER_ID));
                timeTable.setSubjectId(resultSet.getInt(TIME_TABLE_SUBJECT_ID));
                timeTable.setClassId(resultSet.getInt(TIME_TABLE_CLASS_ID));
                timeTable.setClassRoomId(resultSet.getInt(TIME_TABLE_CLASS_ROOM_ID));
                timeTable.setBatchId(resultSet.getInt(BATCH_ID));
                timeTable.setLabId(resultSet.getInt(LAB_ID));
                timeTable.setHrs(resultSet.getInt(TIME_TABLE_HRS));
                timeTable.setType(resultSet.getString(TIME_TABLE_TYPE));
                timeTable.setDayId(resultSet.getInt(DAY_ID));
                timeTable.setTimeId(resultSet.getInt(TIME_ID));
                timeTables.add(timeTable);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return timeTables;
    }

    @Override
    public boolean isTeacherBusy(int classId, int dayId, int timeId,int teacherId) {
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_TIME_TABLE + " WHERE " + TIME_TABLE_CLASS_ID + "=? AND "
                    + DAY_ID + "=? AND " + TIME_ID + "=? AND "+TEACHER_ID+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classId);
            statement.setInt(2, dayId);
            statement.setInt(3, timeId);
            statement.setInt(4, teacherId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TimeTable timeTable = new TimeTable();
                timeTable.setTimeTableId(resultSet.getInt(TIME_TABLE_ID));
                timeTable.setTeacherId(resultSet.getInt(TIME_TABLE_TEACHER_ID));
                timeTable.setSubjectId(resultSet.getInt(TIME_TABLE_SUBJECT_ID));
                timeTable.setClassId(resultSet.getInt(TIME_TABLE_CLASS_ID));
                timeTable.setClassRoomId(resultSet.getInt(TIME_TABLE_CLASS_ROOM_ID));
                timeTable.setBatchId(resultSet.getInt(BATCH_ID));
                timeTable.setLabId(resultSet.getInt(LAB_ID));
                timeTable.setHrs(resultSet.getInt(TIME_TABLE_HRS));
                timeTable.setType(resultSet.getString(TIME_TABLE_TYPE));
                timeTable.setDayId(resultSet.getInt(DAY_ID));
                timeTable.setTimeId(resultSet.getInt(TIME_ID));

                if (timeTable.getClassId() == classId
                        && timeTable.getDayId() == dayId
                        && timeTable.getTimeId() == timeId 
                        && timeTable.getTeacherId() == teacherId) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return isValid;
    }

    @Override
    public List<TimeTable> getTimeTable(int classId, int dayId, int timeId, int teacherId) {
        List<TimeTable> timeTables = new ArrayList();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_TIME_TABLE + " WHERE " + TIME_TABLE_CLASS_ID + "=? AND "
                    + DAY_ID + "=? AND " + TIME_ID + "=? AND "+TIME_TABLE_TEACHER_ID+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classId);
            statement.setInt(2, dayId);
            statement.setInt(3, timeId);
            statement.setInt(4, teacherId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TimeTable timeTable = new TimeTable();
                timeTable.setTimeTableId(resultSet.getInt(TIME_TABLE_ID));
                timeTable.setTeacherId(resultSet.getInt(TIME_TABLE_TEACHER_ID));
                timeTable.setSubjectId(resultSet.getInt(TIME_TABLE_SUBJECT_ID));
                timeTable.setClassId(resultSet.getInt(TIME_TABLE_CLASS_ID));
                timeTable.setClassRoomId(resultSet.getInt(TIME_TABLE_CLASS_ROOM_ID));
                timeTable.setBatchId(resultSet.getInt(BATCH_ID));
                timeTable.setLabId(resultSet.getInt(LAB_ID));
                timeTable.setHrs(resultSet.getInt(TIME_TABLE_HRS));
                timeTable.setType(resultSet.getString(TIME_TABLE_TYPE));
                timeTable.setDayId(resultSet.getInt(DAY_ID));
                timeTable.setTimeId(resultSet.getInt(TIME_ID));
                timeTables.add(timeTable);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return timeTables;
    }

    @Override
    public List<TimeTable> getLabTimeTable(int classId, int dayId, int timeId, int labId) {
       List<TimeTable> timeTables = new ArrayList();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_TIME_TABLE + " WHERE " + TIME_TABLE_CLASS_ID + "=? AND "
                    + DAY_ID + "=? AND " + TIME_ID + "=? AND "+LAB_ID+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classId);
            statement.setInt(2, dayId);
            statement.setInt(3, timeId);
            statement.setInt(4, labId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TimeTable timeTable = new TimeTable();
                timeTable.setTimeTableId(resultSet.getInt(TIME_TABLE_ID));
                timeTable.setTeacherId(resultSet.getInt(TIME_TABLE_TEACHER_ID));
                timeTable.setSubjectId(resultSet.getInt(TIME_TABLE_SUBJECT_ID));
                timeTable.setClassId(resultSet.getInt(TIME_TABLE_CLASS_ID));
                timeTable.setClassRoomId(resultSet.getInt(TIME_TABLE_CLASS_ROOM_ID));
                timeTable.setBatchId(resultSet.getInt(BATCH_ID));
                timeTable.setLabId(resultSet.getInt(LAB_ID));
                timeTable.setHrs(resultSet.getInt(TIME_TABLE_HRS));
                timeTable.setType(resultSet.getString(TIME_TABLE_TYPE));
                timeTable.setDayId(resultSet.getInt(DAY_ID));
                timeTable.setTimeId(resultSet.getInt(TIME_ID));
                timeTables.add(timeTable);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return timeTables; 
    }

    @Override
    public List<TimeTable> getClassRoomTimeTable(int classId, int dayId, int timeId, int classRoomId) {
        List<TimeTable> timeTables = new ArrayList();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_TIME_TABLE + " WHERE " + TIME_TABLE_CLASS_ID + "=? AND "
                    + DAY_ID + "=? AND " + TIME_ID + "=? AND "+TIME_TABLE_CLASS_ROOM_ID+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classId);
            statement.setInt(2, dayId);
            statement.setInt(3, timeId);
            statement.setInt(4, classRoomId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TimeTable timeTable = new TimeTable();
                timeTable.setTimeTableId(resultSet.getInt(TIME_TABLE_ID));
                timeTable.setTeacherId(resultSet.getInt(TIME_TABLE_TEACHER_ID));
                timeTable.setSubjectId(resultSet.getInt(TIME_TABLE_SUBJECT_ID));
                timeTable.setClassId(resultSet.getInt(TIME_TABLE_CLASS_ID));
                timeTable.setClassRoomId(resultSet.getInt(TIME_TABLE_CLASS_ROOM_ID));
                timeTable.setBatchId(resultSet.getInt(BATCH_ID));
                timeTable.setLabId(resultSet.getInt(LAB_ID));
                timeTable.setHrs(resultSet.getInt(TIME_TABLE_HRS));
                timeTable.setType(resultSet.getString(TIME_TABLE_TYPE));
                timeTable.setDayId(resultSet.getInt(DAY_ID));
                timeTable.setTimeId(resultSet.getInt(TIME_ID));
                timeTables.add(timeTable);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return timeTables; 
    }
    

}
