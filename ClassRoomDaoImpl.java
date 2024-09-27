/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.constant.Constant;
import com.dao.ClassRoomDao;
import com.database.Database;
import com.model.ClassRoom;
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
public class ClassRoomDaoImpl implements ClassRoomDao, Constant{

    @Override
    public boolean insertClassRoom(ClassRoom classRoom) {
        boolean isValid = false;
        try {
            String sql = "INSERT INTO "+TABLE_NAME_CLASS_ROOM+
                          " ( "+CLASS_ROOM_ID+","+CLASS_ROOM_NAME+","+CLASS_ROOM_ADD_DATE+
                            ","+STATUS+") "+
                          " VALUES (?,?,?,?) ";
            PreparedStatement statement  = Database.getConnction().prepareStatement(sql);
            statement.setInt(1, classRoom.getClassRoomId());
            statement.setString(2, classRoom.getClassRoomName());
            statement.setString(3, classRoom.getClassRoomAddDate());
            statement.setInt(4, 1);
            int result = statement.executeUpdate();
            if(1 == result){
                isValid = true;
            }
        } catch (SQLException e) {
            Logger.getLogger("ClassRoomDaoImpl.java").log(Level.SEVERE,"Got on excepton",System.err);
        }
        return isValid;
    }

    @Override
    public boolean isClassRoom(String classRoomName) {
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_CLASS_ROOM+" "+
                      " WHERE  "+CLASS_ROOM_NAME+"=?";
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            statement.setString(1, classRoomName);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                if(classRoomName.equals(result.getString(CLASS_ROOM_NAME))){
                    isValid = true;
                    break;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger("ClassRoomDaoImpl.java").log(Level.SEVERE,"Got on excepton",System.err);
        }
        return isValid;
    }

    @Override
    public List<ClassRoom> getAllClassRoom() {
        List<ClassRoom> classRooms = new ArrayList<>();
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_CLASS_ROOM;
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while(result.next()){
              ClassRoom classRoom = new ClassRoom();
              classRoom.setClassRoomName(result.getString(CLASS_ROOM_NAME));
              classRoom.setClassRoomId(result.getInt(CLASS_ROOM_ID));
              classRoom.setClassRoomAddDate(result.getString(CLASS_ROOM_ADD_DATE));
              classRooms.add(classRoom);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return classRooms;
    }

    @Override
    public String getClassRoomName(int classRoomId) {
        String classRoomName = "";
        try {
          String sql = "SELECT * FROM "+TABLE_NAME_CLASS_ROOM+" WHERE "+CLASS_ROOM_ID+"=?";
          PreparedStatement statement = Database.getConnction().prepareStatement(sql);
          statement.setInt(1, classRoomId);
          ResultSet result = statement.executeQuery();
          while(result.next()){
              classRoomName = result.getString(CLASS_ROOM_NAME);
          }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return classRoomName;
    }
    
}
