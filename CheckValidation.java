/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.dao.AddTimeTableDao;
import com.dao.impl.AddTimeTableDaoImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public class CheckValidation {

    public static void main(String[] args) {
        int classId = 1;
        AddTimeTableDao addTimeTableDao = new AddTimeTableDaoImpl();
        //--- Create learning dataset for Theory ------
        List<List<Integer>> theoryDataset = new ArrayList();
        //---- Create learning dataset for Practical and Tutorial ---
        List<List<Integer>> practicalDataset = new ArrayList();
        //----------- Get All Records -----------------
        List<TimeTableDetails> timeTableRecords = addTimeTableDao.getClassTimeDetails(classId);
        System.out.println("Total Records for class ID : " + timeTableRecords.size());
        for (TimeTableDetails timeTableRecord : timeTableRecords) {
            if (timeTableRecord.getType().equals("theory")) {
                int hrsCount = timeTableRecord.getTimeHrs();
                //                                int theroyBlocks  = hrsCount/2;
                //                                int theoryReminderBlocks = hrsCount%2;
                //                                if(theroyBlocks != 0){
                //--- counter to theory block ---
                for (int count = 0; count < hrsCount; count++) {
                    List<Integer> lecture2Hrs = new ArrayList();
                    lecture2Hrs.add(timeTableRecord.getTeacherID());
                    lecture2Hrs.add(timeTableRecord.getSubjectID());
                    lecture2Hrs.add(timeTableRecord.getClassID());
                    lecture2Hrs.add(timeTableRecord.getClassRoomID());
                    lecture2Hrs.add(1);
                    lecture2Hrs.add(2); // type for time table type = Theory
                    theoryDataset.add(lecture2Hrs);
                }
                //                                }
                //                                if(theoryReminderBlocks != 0){
                //                                    List<Integer> lecture1Hrs = new ArrayList();
                //                                        lecture1Hrs.add(timeTableRecord.getTeacherID());
                //                                        lecture1Hrs.add(timeTableRecord.getSubjectID());
                //                                        lecture1Hrs.add(timeTableRecord.getClassID());
                //                                        lecture1Hrs.add(timeTableRecord.getClassRoomID());
                //                                        lecture1Hrs.add(1);
                //                                        theoryDataset.add(lecture1Hrs);
                //                                }

            } else if (timeTableRecord.getType().equals("pratical")) {
                //                                System.out.println("Find Practical Record...");
                int hrsCount = timeTableRecord.getTimeHrs();
                int practicalBlocks = hrsCount / 2;
                int practicalReminderBlocks = hrsCount % 2;
                if (practicalBlocks != 0) {
                    for (int count = 0; count < practicalBlocks; count++) {
                        List<Integer> lecture2Hrs = new ArrayList();
                        lecture2Hrs.add(timeTableRecord.getTeacherID());
                        lecture2Hrs.add(timeTableRecord.getSubjectID());
                        lecture2Hrs.add(timeTableRecord.getClassID());
                        lecture2Hrs.add(timeTableRecord.getLabId());
                        lecture2Hrs.add(timeTableRecord.getBatchId());
                        lecture2Hrs.add(2);
                        lecture2Hrs.add(1); //for type 1=Practical
                        practicalDataset.add(lecture2Hrs);
                    }
                } else if (practicalReminderBlocks != 0) {
                    List<Integer> lecture1Hrs = new ArrayList();
                    lecture1Hrs.add(timeTableRecord.getTeacherID());
                    lecture1Hrs.add(timeTableRecord.getSubjectID());
                    lecture1Hrs.add(timeTableRecord.getClassID());
                    lecture1Hrs.add(timeTableRecord.getLabId());
                    lecture1Hrs.add(timeTableRecord.getBatchId());
                    lecture1Hrs.add(1);
                    lecture1Hrs.add(1); //for type 1=Practical
                    practicalDataset.add(lecture1Hrs);
                }
            } else if (timeTableRecord.getType().equals("tutorial")) {
                //                                System.out.println("Find Tutorial Record...");
                int hrsCount = timeTableRecord.getTimeHrs();
                int practicalBlocks = hrsCount / 2;
                int practicalReminderBlocks = hrsCount % 2;
                if (practicalBlocks != 0) {
                    for (int count = 0; count < practicalBlocks; count++) {
                        List<Integer> lecture2Hrs = new ArrayList();
                        lecture2Hrs.add(timeTableRecord.getTeacherID());
                        lecture2Hrs.add(timeTableRecord.getSubjectID());
                        lecture2Hrs.add(timeTableRecord.getClassID());
                        lecture2Hrs.add(timeTableRecord.getLabId());
                        lecture2Hrs.add(timeTableRecord.getBatchId());
                        lecture2Hrs.add(2);
                        lecture2Hrs.add(3); //for type 1=Tutorial
                        practicalDataset.add(lecture2Hrs);
                    }
                } else if (practicalReminderBlocks != 0) {
                    List<Integer> lecture1Hrs = new ArrayList();
                    lecture1Hrs.add(timeTableRecord.getTeacherID());
                    lecture1Hrs.add(timeTableRecord.getSubjectID());
                    lecture1Hrs.add(timeTableRecord.getClassID());
                    lecture1Hrs.add(timeTableRecord.getLabId());
                    lecture1Hrs.add(timeTableRecord.getBatchId());
                    lecture1Hrs.add(1);
                    lecture1Hrs.add(3); //for type 1=Tutorial
                    practicalDataset.add(lecture1Hrs);
                }
            }
        }

        System.out.println("Size : " + theoryDataset.size() + "  Theory Dataset : " + theoryDataset.toString());
        System.out.println("Size : " + practicalDataset.size() + "  Practical Dataset : " + practicalDataset.toString());
        Collections.shuffle(practicalDataset);
        System.out.println("Shuffle Dataset : "+practicalDataset.toString());
        boolean isEndPractical = false;
        int practicalCounter = practicalDataset.size();
        int practicalCount = 0;
        

        for (int block = 0; block < 10; block++) {
            System.out.println("BLOCK - " + block);
            if (!isEndPractical) {
                if (practicalCounter != practicalCount) {
                    List<Integer> teacherIds = new ArrayList();
                    List<Integer> subjectIds = new ArrayList();
                    List<Integer> labIds = new ArrayList();
                    List<Integer> batchIds = new ArrayList();
                    int count = 0;
                    for (int counter = 0; counter < practicalDataset.size(); counter++) {
                        List<Integer> record = practicalDataset.get(counter);
                        if (teacherIds.contains(record.get(0)) || subjectIds.contains(record.get(1))) {
                            System.out.println("RECORD : "+record.toString());
                            System.out.println("RECORD EXIST ..." + teacherIds.toString()+" "+subjectIds.toString());
                        } else if (teacherIds.isEmpty() || subjectIds.isEmpty() || labIds.isEmpty() || batchIds.isEmpty()) {
                            teacherIds.add(record.get(0));
                            subjectIds.add(record.get(1));
                            labIds.add(record.get(3));
                            batchIds.add(record.get(4));
                            System.out.println("TEACHER IDS : " + teacherIds.toString());
                            System.out.println("ADD RECORD INTO BLOCK " + record);
                            practicalDataset.remove(counter);
//                            System.out.println("TOTAL SIZE OF PRACTICAL DATASET : " + practicalDataset.size());
                            count++;
                            practicalCount++;
                        } else {
                            teacherIds.add(record.get(0));
                            subjectIds.add(record.get(1));
                            labIds.add(record.get(3));
                            batchIds.add(record.get(4));
                            System.out.println("TEACHER IDS : " + teacherIds.toString());
                            System.out.println("ADD RECORD INTO BLOCK " + record.toString());
                            practicalDataset.remove(counter);
                            count++;
                            practicalCount++;
                            if (count == 3) {
                                break;
                            }
                        }

                    }
                } else if (practicalCounter == practicalCount) {
                    isEndPractical = true;
                    System.out.println("END PRACTICAL SLOTS...");
                }
            }
        }
        System.out.println("Remaining Dataset : "+practicalDataset.toString());
    }
}
