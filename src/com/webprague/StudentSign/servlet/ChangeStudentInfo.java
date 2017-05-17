package com.webprague.StudentSign.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webprague.StudentSign.pojo.SignIn;
import com.webprague.StudentSign.pojo.User;
import com.webprague.StudentSign.service.SignInService;
import com.webprague.StudentSign.service.UserService;

@WebServlet("/change_student_info.java")
public class ChangeStudentInfo extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = UserService
				.student_info((String) (request.getSession()
						.getAttribute("username")));
		request.setAttribute("user", user);
		request.getRequestDispatcher("change_student_info.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		String username = request.getParameter("username");
		String sex = request.getParameter("sex");
		String college = request.getParameter("college");
		String phonenumber = request.getParameter("phonenumber");
		String wechat = request.getParameter("wechat");
		String qq = request.getParameter("qq");
		String workplace = request.getParameter("workplace");
		String workcharacter = request.getParameter("workcharacter");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		int changeResult = UserService.change_student_info(username, sex, college, phonenumber, wechat, password, qq, workplace, workcharacter,mail);
		if (changeResult == 0) {
			//修改成功，提示信息修改已成功，返回用户主界面，
			//jump to index_student.java
			// 重定向 ，会改变url，丢失原来的rsx3213equest 与 response
			String msg = "<script>alert('信息修改成功');window.location.href='index_student.jsp';</script>";
			request.setAttribute("msg", msg);
			response.sendRedirect("index_student.java");
			return;
		}

	}
}
