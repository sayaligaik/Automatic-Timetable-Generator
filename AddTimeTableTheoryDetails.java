/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.constant.Constant.TABLE_NAME_TIME_TABLE_DETAILS;
import static com.constant.Constant.TIME_TABLE_ID;
import com.constant.ConstantMethods;
import com.dao.AddTimeTableDao;
import com.dao.SubjectDao;
import com.dao.UserDao;
import com.dao.impl.AddTimeTableDaoImpl;
import com.dao.impl.SubjectDaoImpl;
import com.model.TimeTableDetails;
import com.model.UserImp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ganesh
 */
@WebServlet(name = "AddTimeTableTheoryDetails", urlPatterns = {"/AddTimeTableTheoryDetails"})
public class AddTimeTableTheoryDetails extends HttpServlet {
HttpSession session;
     RequestDispatcher rd;
     String url_path;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession(true);
        ConstantMethods constantMethods = new ConstantMethods();
        url_path = "./add_time_table_theory_details.jsp";
        
        String teacherId = request.getParameter("teacher_id_text");
        String subjectId = request.getParameter("subject_id_text");
        String classId = request.getParameter("class_id_text");
        String classRoomId = request.getParameter("class_room_id_text");
        String type = request.getParameter("type");
        String time = request.getParameter("time_hrs_text");
        String batch = request.getParameter("batch_id_text");
        
        AddTimeTableDao timeTableDao = new AddTimeTableDaoImpl();
        TimeTableDetails timeTableDetails = new TimeTableDetails();
        timeTableDetails.setTeacherID(Integer.parseInt(teacherId));
        timeTableDetails.setSubjectID(Integer.parseInt(subjectId));
        timeTableDetails.setClassID(Integer.parseInt(classId));
        timeTableDetails.setClassRoomID(Integer.parseInt(classRoomId));
        timeTableDetails.setType(type);
        timeTableDetails.setTimeHrs(Integer.parseInt(time));
        timeTableDetails.setBatchId(Integer.parseInt(batch));
        
        if(timeTableDao.isTimeTableTheoryDetails(timeTableDetails)){
            session.setAttribute("msg", "Record already exist!!");
            session.setAttribute("flag", "0");
            UserDao userDao = new UserImp();
            SubjectDao subjectDao = new SubjectDaoImpl();
            request.setAttribute("teacher_id", userDao.getTeacherName(Integer.parseInt(teacherId)));
            request.setAttribute("subject_id", subjectDao.getSubjectName(Integer.parseInt(subjectId)));
            
        }else{
            int newTimeTableId = constantMethods.getNewId(TABLE_NAME_TIME_TABLE_DETAILS, TIME_TABLE_ID);
            timeTableDetails.setTimeTableID(newTimeTableId);
            timeTableDetails.setAddDate(constantMethods.getCurrentDateTime());
            if(timeTableDao.insertTimeTableTheoryDetails(timeTableDetails)){
                session.setAttribute("msg", "Add successfully Teacher time table details!!");
                session.setAttribute("flag", "1");
            }else{
                session.setAttribute("msg", "Somthing is wrong!!");
                session.setAttribute("flag", "0");
            }
        }
        
        rd = request.getRequestDispatcher(url_path);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
