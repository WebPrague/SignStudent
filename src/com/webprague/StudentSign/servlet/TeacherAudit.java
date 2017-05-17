package com.webprague.StudentSign.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webprague.StudentSign.pojo.SignIn;
import com.webprague.StudentSign.service.SignInService;
import com.webprague.StudentSign.service.UserTeacherService;


@WebServlet("/index_teacher.java")
public class TeacherAudit  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("index_teacher.jsp").forward(req, resp);

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String teacher_username = (String) req.getSession().getAttribute(
				"username");
//		List<SignIn>list = SignInService.getSignInTable(teacher_username);
//		req.setAttribute("signer_list",list);

		Integer pagenumber =null;
		try {
			pagenumber = Integer.parseInt(req.getParameter("page"));
		} catch (Exception e) {
			// TODO: handle exception
			pagenumber = 1;
		}
		int usermount = 10;
		String stu_num= req.getParameter("stu_num");
		String current_time = req.getParameter("date");
		try {
			if (current_time != null) {
				List<SignIn>teacher_list = SignInService
						.select_time_TeacherMessage(teacher_username,current_time);
				req.setAttribute("teacher_list", teacher_list);
				req.setAttribute("page_count", 1);
				req.setAttribute("page_number", 1);
			} else {
				req.setAttribute("teacher_list", SignInService.getTeacherPages(teacher_username, pagenumber, usermount));
				Integer pageCount = SignInService.getTeacherPageCount(
						teacher_username, usermount);
				req.setAttribute("page_count", pageCount);
				req.setAttribute("page_number", pagenumber);
			}

			if(stu_num!=null){
				List<SignIn>teacher_list = SignInService.select_stu_num(teacher_username,stu_num);
				req.setAttribute("teacher_list", teacher_list);
				req.setAttribute("page_count", 1);
				req.setAttribute("page_number", 1);
			}else {
				req.setAttribute("teacher_list", SignInService.getTeacherPages(teacher_username, pagenumber, usermount));
				Integer pageCount = SignInService.getTeacherPageCount(
						teacher_username, usermount);
				req.setAttribute("page_count", pageCount);
				req.setAttribute("page_number", pagenumber);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		req.getRequestDispatcher("index_teacher.jsp").forward(req, resp);






	}
}
