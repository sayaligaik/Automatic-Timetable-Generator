/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.TimeTableDetails;
import java.util.List;

/**
 *
 * 
 */
public interface AddTimeTableDao {
    public boolean insertTimeTablePracticalDetails(TimeTableDetails timeTableDetails);
    public boolean isTimeTablePracticalDetails(TimeTableDetails timeTableDetails);
    
    public boolean insertTimeTableTheoryDetails(TimeTableDetails timeTableDetails);
    public boolean isTimeTableTheoryDetails(TimeTableDetails timeTableDetails);
    
    public List<TimeTableDetails> getClassTimeDetails(int classID);
    public boolean isValidClassTimeDetailsForPractical(int classId);
    public boolean isValidClassTimeDetailsForTheory(int classId);
    
    
}
