/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Lab;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public interface LabDao {
    public boolean insertLab(Lab lab);
    public boolean isLab(String labName);
    public List<Lab> getAllLab();
    public String getLabName(int labId);
}
