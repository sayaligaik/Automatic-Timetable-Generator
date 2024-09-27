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
public class DaysImpl implements DaysDao, Constant{

    @Override
    public List<Days> getDays() {
        List<Days> daysList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM "+TABLE_NAME_TABLE_DAYS;
            PreparedStatement statement = Database.getConnction().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Days days = new Days();
                days.setDayId(result.getInt(DAY_ID));
                days.setDay(result.getString(DAY));
                daysList.add(days);
            }
        } catch (SQLException e) {
            Logger.getLogger("DaysImpl.java").log(Level.SEVERE, "Got on exception ",e.getMessage());
        }
        return daysList;
    }
    
}
