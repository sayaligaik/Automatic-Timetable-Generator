package com.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.constant.ConstantMethods;
import com.dao.UserDao;
import com.model.User;
import com.model.UserImp;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;
    String url_path;
    RequestDispatcher rd;
    HttpSession session;
    ConstantMethods constant;

    protected void process(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(true);
        constant = new ConstantMethods();
        UserDao userDao = new UserImp();
        // get user parameter [ user_name and user_password ]
        String user_name = request.getParameter("email_text");
        String user_password = request.getParameter("password_text");
        String user_type = request.getParameter("user_type_text");
        if (constant.isLogin(user_name, user_password, user_type)) {
            switch (user_type) {
                case "Teacher":
                    url_path = "./teacher_home.jsp";
                    session.setAttribute("msg", "Login success.");
                    session.setAttribute("flag", "1");
                    session.setAttribute("user_name_session", user_name);
                    session.setAttribute("user_type", user_type);
                    User userData = userDao.getUserDetailsByEmailId(user_name, user_type);
                    session.setAttribute("userId", userData.getUserId());
                    
                    break;
                case "Incharge":
                    url_path = "./incharge_home.jsp";
                    session.setAttribute("msg", "Login success.");
                    session.setAttribute("flag", "1");
                    session.setAttribute("user_name_session", user_name);
                    session.setAttribute("user_type", user_type);
                     User userData1 = userDao.getUserDetailsByEmailId(user_name, user_type);
                    session.setAttribute("userId", userData1.getUserId());
                    break;
                default:
                    break;
            }

        } else {
            url_path = "./login.jsp";
            session.setAttribute("msg", "Please check user name and password.");
            session.setAttribute("flag", "0");
        }

        rd = request.getRequestDispatcher(url_path);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

}
