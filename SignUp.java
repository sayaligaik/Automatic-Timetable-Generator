package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.constant.Constant;
import com.constant.ConstantMethods;
import com.model.User;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet implements Constant {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
	String url_path;
	HttpSession session;
	User user;
	ConstantMethods method;

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
			url_path = "./signup.jsp";
			session.setAttribute("msg", "This username is already registered.");
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
				url_path = "./login.jsp";
				session.setAttribute("msg", "You have signup successfully.");
				session.setAttribute("flag", "1");
			} else {
				url_path = "./signup.jsp";
				session.setAttribute("msg",
						"Something is wrong. Please try again.");
				session.setAttribute("flag", "0");
			}
		}
		rd = request.getRequestDispatcher(url_path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
