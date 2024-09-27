/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;


import com.constant.Constant;
import static com.constant.Constant.USER_ID;
import com.constant.ConstantMethods;
import com.model.User;
import java.io.IOException;
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
@WebServlet(name = "AddTeacher", urlPatterns = {"/AddTeacher"})
public class AddTeacher extends HttpServlet implements Constant{

    RequestDispatcher rd;
    String url_path;
    HttpSession session;
    User user;
    ConstantMethods method;

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
        String user_name = request.getParameter("name_text");
        String user_password = request.getParameter("password_text");
        String user_email_id = request.getParameter("email_id_text");
        String user_type = request.getParameter("user_type_text");

        user = new User();
        session = request.getSession(true);
        method = new ConstantMethods();
        int userId = method.getNewId(TABLE_NAME_USER, USER_ID);
        // Check user is register
        if (method.isUser(user_email_id, user_type)) {
            url_path = "./add_teacher.jsp";
            session.setAttribute("msg", "This teacher is already registered.");
            session.setAttribute("flag", "0");
        } else {

            user.setUserId(userId);
            user.setUserName(user_name);
            user.setUserEmailID(user_email_id);
            user.setUserPassword(user_password);
            user.setUserType(user_type);
            user.setStatus("1");
            user.setUserRegDate(method.getCurrentDateTime());
            if (method.isRegiter(user)) {
                url_path = "./add_teacher.jsp";
                session.setAttribute("msg", "Add teacher successfully.");
                session.setAttribute("flag", "1");
            } else {
                url_path = "./add_teacher.jsp";
                session.setAttribute("msg","Something is wrong. Please try again.");
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
