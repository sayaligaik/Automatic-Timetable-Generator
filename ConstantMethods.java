package com.constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import com.database.Database;
import com.model.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConstantMethods implements Constant {

    public int getNewId(String tableName, String columnName) {
        int id = 0;
        try {
            String sql = "SELECT MAX(" + columnName + ") FROM " + tableName;
            Connection connection = Database.getConnction();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return (id + 1);
    }

    public boolean isRegiter(User user) {
        boolean isValid = false;
        try {
            String sql = "INSERT INTO " + TABLE_NAME_USER + "(" + USER_ID + ","
                    + USER_NAME + "," + USER_EMAIL_ID + "," + USER_PASSWORD
                    + "," + USER_TYPE + "," + REGISTER_DATE + "," + STATUS
                    + ") VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = Database.getConnction()
                    .prepareStatement(sql);
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserEmailID());
            statement.setString(4, user.getUserPassword());
            statement.setString(5, user.getUserType());
            statement.setString(6, user.getUserRegDate());
            statement.setString(7, user.getStatus());
            int result = statement.executeUpdate();
            if (1 == result) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public boolean isUser(String email_id, String user_type) {
        boolean isValid = false;
        try {
            String sql = "SELECT " + USER_EMAIL_ID + "," + USER_TYPE + " FROM "
                    + TABLE_NAME_USER + " WHERE " + USER_EMAIL_ID + "=? AND "
                    + USER_TYPE + "=?";
            PreparedStatement statement = Database.getConnction()
                    .prepareStatement(sql);
            statement.setString(1, email_id);
            statement.setString(2, user_type);
            ResultSet result = statement.executeQuery();
            User user = new User();
            while (result.next()) {
                user.setUserEmailID(result.getString(USER_EMAIL_ID));
                user.setUserType(result.getString(USER_TYPE));
                if (user.getUserEmailID().equals(email_id)
                        && user.getUserType().equals(user_type)) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public boolean isLogin(String user_name, String user_password,
            String user_type) {
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_USER + " WHERE "
                    + USER_EMAIL_ID + "=? AND " + USER_PASSWORD + "=? AND " + STATUS + "=? AND " + USER_TYPE + "=?";
            PreparedStatement statement = Database.getConnction()
                    .prepareStatement(sql);
            statement.setString(1, user_name);
            statement.setString(2, user_password);
            statement.setInt(3, 1);
            statement.setString(4, user_type);
            ResultSet result = statement.executeQuery();
            User user = new User();
            while (result.next()) {
                user.setUserEmailID(result.getString(USER_EMAIL_ID));
                user.setUserPassword(result.getString(USER_PASSWORD));
                user.setStatus(result.getString(STATUS));
                if (user.getUserEmailID().equals(user_name)) {
                    if (user.getUserPassword().equals(user_password)) {
                        isValid = true;
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger("ConstantMethod.java").log(Level.WARNING, "Got Exception:", e.getMessage());
        }
        return isValid;
    }

    public String getCurrentDateTime() {
        String date = "";
        try {
            Calendar calender = new GregorianCalendar();
            int day = calender.get(Calendar.DAY_OF_MONTH);
            int month = calender.get(Calendar.MONTH);
            int year = calender.get(Calendar.YEAR);
            int hour = calender.get(Calendar.HOUR);
            int minute = calender.get(Calendar.MINUTE);
            int second = calender.get(Calendar.SECOND);
            date = year + "-" + (month + 1) + "-" + day + " " + hour + ":" + minute + ":" + second;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
