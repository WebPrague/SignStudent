package com.webprague.StudentSign.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webprague.StudentSign.service.SignInService;
import com.webprague.StudentSign.service.UserTeacherService;
@WebServlet("/sign_off.java")
public class SignOutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String remark = request.getParameter("remark");
		String student_username = (String)request.getSession().getAttribute("username");

		SignInService.add_signout(student_username,remark);
		//response.sendRedirect("index_student.java");
		request.setAttribute("msg", "签离成功");
		request.setAttribute("location", "index_student.java");
		request.getRequestDispatcher("jump.jsp").forward(request, response);

	}



}
