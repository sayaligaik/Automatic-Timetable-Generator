/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.TimeTable;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public interface TimeTableDao {
    public boolean insertTimeTable(TimeTable timeTable);
    public boolean rejectTimeTable(int classId);
    public List<TimeTable> getTimeTable(int classId, int dayId, int timeId);
    public boolean isTeacherBusy(int classId,int dayId,int timeId,int teacherId);
    public List<TimeTable> getTimeTable(int classId, int dayId, int timeId, int teacherId);
    public List<TimeTable> getLabTimeTable(int classId, int dayId, int timeId, int labId);
    public List<TimeTable> getClassRoomTimeTable(int classId, int dayId, int timeId, int classRoomId);
}
