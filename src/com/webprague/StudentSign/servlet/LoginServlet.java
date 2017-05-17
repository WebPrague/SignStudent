package com.webprague.StudentSign.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webprague.StudentSign.service.UserService;
import com.webprague.StudentSign.service.UserTeacherService;

@WebServlet("/login.java")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();
		//		session.setAttribute("username", username);
		//		session.setAttribute("password", password);
		boolean login_teacherResult = UserTeacherService.login_teacher(username, password);
		if(login_teacherResult){

			String teacher_num = username;
			session.setAttribute("username", teacher_num);
			session.setAttribute("name", UserTeacherService.getNameByUsername(username));
			session.setAttribute("teacher", true);
			resp.sendRedirect("index_teacher.java");
		}else {
			boolean login_studentResult =UserService.login_student(username, password);
			if(login_studentResult){
				String student_num = username;
				session.setAttribute("username", username);
				session.setAttribute("teacher", false);
				session.setAttribute("name", UserService.getNameByUsername(username));
				resp.sendRedirect("index_student.java");
			}else {
				req.setAttribute("msg", "<script>alert('用户名或者密码错误！');</script>");
				//转发 不改变url
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

}
