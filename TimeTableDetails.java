/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Ganesh
 */
public class TimeTableDetails {
    int timeTableID;
    int teacherID;
    int subjectID;
    int classID;
    int classRoomID;
    String type;
    int timeHrs;
    String addDate;
    int batchId;
    String status;
    int labId;

    public int getTimeTableID() {
        return timeTableID;
    }

    public void setTimeTableID(int timeTableID) {
        this.timeTableID = timeTableID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getClassRoomID() {
        return classRoomID;
    }

    public void setClassRoomID(int classRoomID) {
        this.classRoomID = classRoomID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTimeHrs() {
        return timeHrs;
    }

    public void setTimeHrs(int timeHrs) {
        this.timeHrs = timeHrs;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }
    
    
    
}
