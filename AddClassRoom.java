/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.constant.Constant;
import com.constant.ConstantMethods;
import com.dao.ClassRoomDao;
import com.dao.impl.ClassRoomDaoImpl;
import com.model.ClassRoom;
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
@WebServlet(name = "AddClassRoom", urlPatterns = {"/AddClassRoom"})
public class AddClassRoom extends HttpServlet implements Constant {

    String url_path;
    HttpSession session;
    RequestDispatcher rd;

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
        ClassRoom classRoom = new ClassRoom();
        ClassRoomDao classRoomDao = new ClassRoomDaoImpl();
        ConstantMethods constantMethods = new ConstantMethods();

        String clasRoomName = request.getParameter("class_room_name_text");

        int newClassRoomID = constantMethods.getNewId(TABLE_NAME_CLASS_ROOM, CLASS_ROOM_ID);
        classRoom.setClassRoomId(newClassRoomID);
        classRoom.setClassRoomName(clasRoomName);
        classRoom.setClassRoomAddDate(constantMethods.getCurrentDateTime());
        classRoom.setStatus("1");

        url_path = "./add_class_room.jsp";
        if (!classRoomDao.isClassRoom(clasRoomName)) {
            if (classRoomDao.insertClassRoom(classRoom)) {
                session.setAttribute("msg", "Class room add successfully!!..");
                session.setAttribute("flag", "1");
            } else {
                session.setAttribute("msg", "Somthing is wrong!!..");
                session.setAttribute("flag", "0");
            }
        } else {
            session.setAttribute("msg", "Class room already exist!!..");
            session.setAttribute("flag", "0");
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
