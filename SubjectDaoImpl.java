/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.constant.Constant;
import com.dao.SubjectDao;
import com.database.Database;
import com.model.Subject;
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
public class SubjectDaoImpl implements SubjectDao, Constant {

    @Override
    public boolean addSubject(Subject subject) {
        boolean isValid = false;
        try {
            String sql = "INSERT INTO  " + TABLE_NAME_SUBJECT + " ( "
                    + SUBJECT_ID + "," + SUBJECT_NAME + "," + SUBJECT_ADD_DATE
                    + "," + STATUS + ") "
                    + " VALUES(?,?,?,?)";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, subject.getSubjectId());
            statement.setString(2, subject.getSubjectName());
            statement.setString(3, subject.getSubjectAddDate());
            statement.setString(4, subject.getStatus());
            int result = statement.executeUpdate();
            if (1 == result) {
                isValid = true;
            }
        } catch (SQLException e) {
            Logger.getLogger("SubjectDaoImpl.java : ").log(Level.SEVERE, "Got on exception  ", e.getMessage());
        }
        return isValid;
    }

    @Override
    public boolean deleteSubject(int subjectId) {
        boolean isValid = false;
        try {
            String sql = "DELETE FROM " + TABLE_NAME_SUBJECT + " WHERE " + SUBJECT_ID + "=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, subjectId);
            int result = statement.executeUpdate();
            if (1 == result) {
                isValid = true;
            }
        } catch (SQLException e) {
            Logger.getLogger("SubjectDaoImpl.java : ").log(Level.SEVERE, "Got on exception {%s} ", e.getMessage());
        }
        return isValid;
    }

    @Override
    public boolean updateSubject(Subject subject) {
        boolean isValid = false;
        try {
            String sql = "UPDATE " + TABLE_NAME_SUBJECT + " SET " + SUBJECT_NAME + "=? "
                    + " WHERE " + SUBJECT_ID + "=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setString(1, subject.getSubjectName());
            statement.setInt(2, subject.getSubjectId());
            int result = statement.executeUpdate();
            if (1 == result) {
                isValid = true;
            }
        } catch (SQLException e) {
            Logger.getLogger("SubjectDaoImpl.java : ").log(Level.SEVERE, "Got on exception {%s} ", e.getMessage());
        }
        return isValid;
    }

    @Override
    public List<Subject> getAllSubject() {
        List<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_SUBJECT;
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(result.getInt(SUBJECT_ID));
                subject.setSubjectName(result.getString(SUBJECT_NAME));
                subject.setSubjectAddDate(result.getString(SUBJECT_ADD_DATE));
                subject.setStatus(result.getString(STATUS));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            Logger.getLogger("SubjectDaoImpl.java : ").log(Level.SEVERE, "Got on exception {%s} ", e.getMessage());
        }
        return subjects;
    }

    @Override
    public boolean isSubject(String subjectName) {
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM   " + TABLE_NAME_SUBJECT + " ( "
                    + SUBJECT_ID + "=? ";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setString(1, subjectName);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (subjectName.equals(result.getString(SUBJECT_NAME))) {
                    isValid = true;
                    break;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger("SubjectDaoImpl.java : ").log(Level.SEVERE, "Got on exception {%s} ", System.err);
        }
        return isValid;
    }

    @Override
    public String getSubjectName(int subjectId) {
        String subjectName = "";
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_SUBJECT + " WHERE  " + SUBJECT_ID + "=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, subjectId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                subjectName = result.getString(SUBJECT_NAME);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return subjectName;
    }

    @Override
    public List<List<String>> getSubjects(int teacherId) {
        List<List<String>> subjects = new ArrayList();
        try {
            String sql = "SELECT DISTINCT(subject.sub_name),user.name,class.class_name FROM time_table,subject,class,user,batch where user.u_id=time_table.teacher_id "
                    + "and user.u_id=? and time_table.subject_id=subject.sub_id and time_table.class_id=class.c_id";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, teacherId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                List<String> list = new ArrayList();
                list.add(result.getString("sub_name"));
                list.add(result.getString("name"));
                list.add(result.getString("class_name"));
                subjects.add(list);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return subjects;
    }

}
