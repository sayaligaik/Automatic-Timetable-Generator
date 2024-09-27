/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.constant.Constant;
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
public class TimeImpl implements TimeDao,Constant{

    @Override
    public List<Time> getTimeBlocks() {
        List<Time> times = new ArrayList<>();
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_TIME_TABLE_TIME;
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Time time = new Time();
                time.setTimeId(result.getInt(TIME_ID));
                time.setTimeBlock(result.getString(TIME_BLOCK));
                time.setTimeReason(result.getInt(TIME_REASON));
                times.add(time);
            }
        } catch (SQLException e) {
            Logger.getLogger("TimeImpl.java").log(Level.SEVERE, "Go on exception ",e.getMessage());
        }
        return times;
    }
    
}
