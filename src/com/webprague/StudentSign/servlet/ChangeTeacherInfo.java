package com.webprague.StudentSign.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webprague.StudentSign.pojo.UserTeacher;
import com.webprague.StudentSign.service.UserTeacherService;


@WebServlet("/change_teacher_info.java")
public class ChangeTeacherInfo extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserTeacher user_teacher = UserTeacherService
				.teacher_info((String) (request.getSession()
						.getAttribute("username")));
		request.setAttribute("user_teacher", user_teacher);
		request.getRequestDispatcher("change_teacher_info.jsp").forward(request, response);
		return;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String department = request.getParameter("department");
		String mail = request.getParameter("mail");
		int changeResult = UserTeacherService.change_teacher_info(username, name, password, department,mail);
		if (changeResult == 0) {
			//修改成功，提示信息修改已成功，返回用户主界面，
			//jump to index_student.java
			// 重定向 ，会改变url，丢失原来的rsx3213equest 与 response
			String msg = "<script>alert('信息修改成功');window.location.href='index_teacher.jsp';</script>";
			request.setAttribute("msg", msg);
			response.sendRedirect("index_teacher.java");
			return;
		}
	}

}
