/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.constant.Constant;
import com.dao.BatchDao;
import com.dao.ClassDao;
import com.dao.ClassRoomDao;
import com.dao.LabDao;
import com.dao.SubjectDao;
import com.dao.TimeTableDao;
import com.dao.UserDao;
import com.dao.impl.BatchDaoImpl;
import com.dao.impl.ClassDaoImpl;
import com.dao.impl.ClassRoomDaoImpl;
import com.dao.impl.LabDaoImpl;
import com.dao.impl.SubjectDaoImpl;
import com.dao.impl.TimeTableDaoImpl;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Ganesh
 */
public class PDFFileCreate implements Constant {

    public static void createTimeTable(int className) {
        TimeTableDao timeTableDao = new TimeTableDaoImpl();
        ClassDao classDao = new ClassDaoImpl();
        DaysDao daysDao = new DaysImpl();
        TimeDao timeDao = new TimeImpl();
        UserDao userDao = new UserImp();
        SubjectDao subjectDao = new SubjectDaoImpl();
        BatchDao batchDao = new BatchDaoImpl();
        LabDao labDao = new LabDaoImpl();
        ClassRoomDao classRoomDao = new ClassRoomDaoImpl();
        java.util.List<Days> days = daysDao.getDays();
        java.util.List<Time> times = timeDao.getTimeBlocks();
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(TIME_TABLE_PDF_FILE_PATH + "timeTableFor" + classDao.getClassName(className) + ".pdf"));
            document.open();
            PdfPTable table = new PdfPTable(times.size());
            Font black = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
            StringBuilder headerBuilder = new StringBuilder();
            headerBuilder.append("TIME TABLE").append("\n\n");
            headerBuilder.append("Class : ").append(classDao.getClassName(className));
            PdfPCell cell = new PdfPCell(new Paragraph(headerBuilder.toString()));
            cell.setColspan(times.size());
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(10.0f);
            table.addCell(cell);
            java.util.List<TimeTable> timeTable;
            PdfPCell cellPractical;
            for (Time time : times) {
                Chunk blueText = new Chunk(time.getTimeBlock(), black);
                cellPractical = new PdfPCell(new Paragraph(blueText));
                cellPractical.setColspan(1);
                table.addCell(cellPractical);
            }

            for (Days day : days) {
                for (Time time : times) {
                    switch (time.getTimeId()) {
                        case 0:
                            table.addCell(day.getDay());
                            break;
                        case 1:
                            StringBuilder builder = new StringBuilder();
                            timeTable = timeTableDao.getTimeTable(className, day.getDayId(), time.getTimeId());
                            for (TimeTable record : timeTable) {
                                builder.append(subjectDao.getSubjectName(record.getSubjectId())).append(" - (").append(record.getType());
                                builder.append(")-").append(classRoomDao.getClassRoomName(record.getClassRoomId()));
                                builder.append("-").append(userDao.getTeacherName(record.getTeacherId()));
                            }
                            Chunk blueText = new Chunk(builder.toString(), black);
                            cellPractical = new PdfPCell(new Paragraph(blueText));
                            cellPractical.setColspan(1);
                            table.addCell(cellPractical);
                            break;
                        case 2:
                            StringBuilder builderCase2 = new StringBuilder();
                            timeTable = timeTableDao.getTimeTable(className, day.getDayId(), time.getTimeId());
                            for (TimeTable record : timeTable) {
                                builderCase2.append(subjectDao.getSubjectName(record.getSubjectId())).append(" - (").append(record.getType());
                                builderCase2.append(")-").append(classRoomDao.getClassRoomName(record.getClassRoomId()));
                                builderCase2.append("-").append(userDao.getTeacherName(record.getTeacherId()));
                            }
                            Chunk blueTextCase2 = new Chunk(builderCase2.toString(), black);
                            cellPractical = new PdfPCell(new Paragraph(blueTextCase2));
                            cellPractical.setColspan(1);
                            table.addCell(cellPractical);
                            break;

                        case 3:
                            table.addCell("Break");
                            break;
                        case 4:
                            StringBuilder builderCase4 = new StringBuilder();
                            timeTable = timeTableDao.getTimeTable(className, day.getDayId(), time.getTimeId());
                            for (TimeTable record : timeTable) {
                                if (record.getType().equals("theory")) {
                                    builderCase4.append(subjectDao.getSubjectName(record.getSubjectId())).append("-");
                                    builderCase4.append(record.getType()).append("-");
                                    builderCase4.append(classRoomDao.getClassRoomName(record.getClassRoomId())).append(userDao.getTeacherName(record.getTeacherId())).append("\n");
                                } else if (record.getType().equals("practical")) {
                                    builderCase4.append(subjectDao.getSubjectName(record.getSubjectId())).append("-");
                                    builderCase4.append(record.getType()).append("-");
                                    builderCase4.append(labDao.getLabName(record.getLabId())).append("-");
                                    builderCase4.append(batchDao.getBatchName(record.getBatchId()))
                                            .append("-").append(userDao.getTeacherName(record.getTeacherId())).append("\n");
                                    builderCase4.append("---------------").append("\n");

                                } else if (record.getType().equals("tutorial")) {
                                    builderCase4.append(subjectDao.getSubjectName(record.getSubjectId())).append("-");
                                    builderCase4.append(record.getType()).append("-");
                                    builderCase4.append(labDao.getLabName(record.getLabId())).append("-");
                                    builderCase4.append(batchDao.getBatchName(record.getBatchId()))
                                            .append("-").append(userDao.getTeacherName(record.getTeacherId())).append("\n");
                                    builderCase4.append("---------------").append("\n");
                                }
                            }
                            Chunk blueTextCase4 = new Chunk(builderCase4.toString(), black);
                            cellPractical = new PdfPCell(new Paragraph(blueTextCase4));
                            cellPractical.setColspan(2);
                            table.addCell(cellPractical);

                            break;
                        case 5:

                            break;
                        case 6:
                            StringBuilder builderCase6 = new StringBuilder();
                            timeTable = timeTableDao.getTimeTable(className, day.getDayId(), time.getTimeId());
                            for (TimeTable record : timeTable) {
                                builderCase6.append(subjectDao.getSubjectName(record.getSubjectId())).append(" - (").append(record.getType());
                                builderCase6.append(")-").append(classRoomDao.getClassRoomName(record.getClassRoomId()));
                                builderCase6.append("-").append(userDao.getTeacherName(record.getTeacherId()));
                            }
                            Chunk blueTextCase6 = new Chunk(builderCase6.toString(), black);
                            cellPractical = new PdfPCell(new Paragraph(blueTextCase6));
                            cellPractical.setColspan(1);
                            table.addCell(cellPractical);
                            break;
                        case 7:
                            StringBuilder builderCase7 = new StringBuilder();
                            timeTable = timeTableDao.getTimeTable(className, day.getDayId(), time.getTimeId());
                            for (TimeTable record : timeTable) {
                                builderCase7.append(subjectDao.getSubjectName(record.getSubjectId())).append(" - (").append(record.getType());
                                builderCase7.append(")-").append(classRoomDao.getClassRoomName(record.getClassRoomId()));
                                builderCase7.append("-").append(userDao.getTeacherName(record.getTeacherId()));
                            }
                            Chunk blueTextCase7 = new Chunk(builderCase7.toString(), black);
                            cellPractical = new PdfPCell(new Paragraph(blueTextCase7));
                            cellPractical.setColspan(1);
                            table.addCell(cellPractical);
                            break;

                        case 8:
                            StringBuilder builderCase8 = new StringBuilder();
                            timeTable = timeTableDao.getTimeTable(className, day.getDayId(), time.getTimeId());
                            for (TimeTable record : timeTable) {
                                if (record.getType().equals("theory")) {
                                    builderCase8.append(subjectDao.getSubjectName(record.getSubjectId())).append("-");
                                    builderCase8.append(record.getType()).append("-");
                                    builderCase8.append(classRoomDao.getClassRoomName(record.getClassRoomId())).append(userDao.getTeacherName(record.getTeacherId())).append("\n");
                                } else if (record.getType().equals("practical")) {
                                    builderCase8.append(subjectDao.getSubjectName(record.getSubjectId())).append("-");
                                    builderCase8.append(record.getType()).append("-");
                                    builderCase8.append(labDao.getLabName(record.getLabId())).append("-");
                                    builderCase8.append(batchDao.getBatchName(record.getBatchId()))
                                            .append("-").append(userDao.getTeacherName(record.getTeacherId())).append("\n");
                                    builderCase8.append("---------------").append("\n");

                                } else if (record.getType().equals("tutorial")) {
                                    builderCase8.append(subjectDao.getSubjectName(record.getSubjectId())).append("-");
                                    builderCase8.append(record.getType()).append("-");
                                    builderCase8.append(labDao.getLabName(record.getLabId())).append("-");
                                    builderCase8.append(batchDao.getBatchName(record.getBatchId()))
                                            .append("-").append(userDao.getTeacherName(record.getTeacherId())).append("\n");
                                    builderCase8.append("---------------").append("\n");
                                }
                            }
                            Chunk blueTextCase8 = new Chunk(builderCase8.toString(), black);
                            cellPractical = new PdfPCell(new Paragraph(blueTextCase8));
                            cellPractical.setColspan(2);
                            table.addCell(cellPractical);
                            break;
                        default:
                            break;

                    }
                }
            }
            //------ CREATE FOOTER ----------------
            StringBuilder footerBuilder = new StringBuilder();

            footerBuilder.append("Head Of Department").append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
//            footerBuilder.append("Time Table Incharge").append("\t\t\t\t");
            footerBuilder.append("Principal").append("\n");
            footerBuilder.append("").append("\n");
            footerBuilder.append("").append("\n");
            footerBuilder.append("").append("\n");
            footerBuilder.append("(IT Dept.)").append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
//            footerBuilder.append("(IT Dept.)").append("\t\t\t\t");
            footerBuilder.append("( Government Polytechnic, Pune)").append("\n");
            Chunk footerChunk = new Chunk(footerBuilder.toString(), black);
            PdfPCell footerCell = new PdfPCell(new Paragraph(footerChunk));
            footerCell.setColspan(times.size());
            footerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            footerCell.setBackgroundColor(BaseColor.WHITE);
            footerCell.setPadding(28.0f);
            table.addCell(footerCell);
            document.add(table);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace(System.err);
        }

    }

}
