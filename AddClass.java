/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.constant.Constant;
import com.constant.ConstantMethods;
import com.dao.ClassDao;
import com.dao.impl.ClassDaoImpl;
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
@WebServlet(name = "AddClass", urlPatterns = {"/AddClass"})
public class AddClass extends HttpServlet implements Constant{
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
        url_path = "./add_class.jsp";
        
        ConstantMethods constantMethods = new ConstantMethods();
        com.model.Class class_ = new com.model.Class();
        ClassDao classDao = new ClassDaoImpl();
        
        String className = request.getParameter("class_name_text");
        
        class_.setClassId(constantMethods.getNewId(TABLE_NAME_CLASS, CLASS_ID));
        class_.setClassName(className);
        class_.setClassAddDate(constantMethods.getCurrentDateTime());
        class_.setStatus(1);
        
        if(classDao.isClass(className)){
           session.setAttribute("msg", "Class name already exist!!");
           session.setAttribute("flag", "0");
        }else{
            if(classDao.addClass(class_)){
                session.setAttribute("msg", "Class name add successfully!!");
                 session.setAttribute("flag", "1");
            }else{
                session.setAttribute("msg", "Somthing ie wrong!!");
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
