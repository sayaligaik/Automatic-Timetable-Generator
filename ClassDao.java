/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.util.List;

/**
 *
 * @author Ganesh
 */
public interface ClassDao {
    public boolean isClass(String className);
    public boolean addClass(com.model.Class class_);
    public List<com.model.Class> getAllClass(); 
    public String getClassName(int classId);
}
