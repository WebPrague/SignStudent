package com.webprague.StudentSign.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webprague.StudentSign.pojo.SignIn;
import com.webprague.StudentSign.pojo.UserTeacher;
import com.webprague.StudentSign.service.SignInService;
import com.webprague.StudentSign.service.UserService;
import com.webprague.StudentSign.service.UserTeacherService;

@WebServlet("/index_student.java")
public class Student extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String student_username = (String) req.getSession().getAttribute(
				"username");
		Integer pagenumber = null;
		try {
			pagenumber = Integer.parseInt(req.getParameter("page"));
		} catch (Exception e) {
			// TODO: handle exception
			pagenumber = 1;
		}
		// req.setAttribute("signusers",
		// SignInService.getAllmessage(student_username));

		int usermount = 10;

		req.setAttribute("teachers", UserTeacherService.getAllTeachcers());
		SignIn signIn = SignInService
				.getNotSignOutSignObjectByUsername((String) (req.getSession()
						.getAttribute("username")));
		req.setAttribute("sign", signIn);

		String current_time = req.getParameter("date");
		try {
			if (current_time != null) {
				List<SignIn> list = SignInService
						.select_time_message(current_time);
				req.setAttribute("sign_list", list);
				req.setAttribute("page_count", 1);
				req.setAttribute("page_number", 1);
			} else {
				req.setAttribute("sign_list", SignInService.getPages(
						student_username, pagenumber, usermount));
				Integer pageCount = SignInService.getPageCount(
						student_username, usermount);
				req.setAttribute("page_count", pageCount);
				req.setAttribute("page_number", pagenumber);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("index_student.jsp").forward(req, resp);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getRequestDispatcher("index_student.jsp").forward(req, resp);
	}

}
