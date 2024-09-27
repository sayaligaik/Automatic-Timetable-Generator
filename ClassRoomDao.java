/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.ClassRoom;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public interface ClassRoomDao {
    public boolean insertClassRoom(ClassRoom classRoom);
    public boolean isClassRoom(String classRoomName);
    public List<ClassRoom> getAllClassRoom();
    public String getClassRoomName(int classRoomId);
}
