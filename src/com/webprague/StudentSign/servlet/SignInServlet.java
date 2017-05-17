package com.webprague.StudentSign.servlet;

import java.io.IOException;


import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webprague.StudentSign.pojo.SignIn;
import com.webprague.StudentSign.service.SignInService;
import com.webprague.StudentSign.service.UserService;

@WebServlet("/sign_in.java")
public class SignInServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String teacher_id = req.getParameter("teacher_username");
		String student_username = (String)req.getSession().getAttribute("username");
		String student_name = UserService.getNameByUsername(student_username);
		Timestamp signin_time = new Timestamp(System.currentTimeMillis());
		SignInService.add_signin(student_username,student_name,signin_time,teacher_id);
		//req.getRequestDispatcher("sign_in.jsp").forward(req, resp);

		req.setAttribute("msg", "签到成功");
		req.setAttribute("location", "index_student.java");
		req.getRequestDispatcher("jump.jsp").forward(req, resp);
		//resp.sendRedirect("index_student.java");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		req.getRequestDispatcher("sign_in.jsp").forward(req, resp);
	}
}
