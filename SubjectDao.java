/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Subject;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public interface SubjectDao {
    public boolean addSubject(Subject subject);
    public boolean isSubject(String subjectName);
    public boolean deleteSubject(int subjectId);
    public boolean updateSubject(Subject subject);
    public List<Subject> getAllSubject();
    public String getSubjectName(int subjectId);
    public List<List<String>>  getSubjects(int teacherId); 
//    public List<Subject> getTeacherSubject();
}
