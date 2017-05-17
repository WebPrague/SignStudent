package com.webprague.StudentSign.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.webprague.StudentSign.service.UserService;
import com.webprague.StudentSign.service.UserTeacherService;

public class Login extends ActionSupport{
	private String username;
	private String password;
	private Map<String , Object>session;
	//	private HttpSession session2;
	@Override
	public String execute() throws Exception {
		if (ServletActionContext.getRequest().getMethod().equals("POST")) {
			this.session = ActionContext.getContext().getSession();
//			this.session2 = ServletActionContext.getRequest().getSession();
			boolean login_teacherResult = UserTeacherService.login_teacher(
					username, password);
			if (login_teacherResult) {
				String teacher_num = username;
				this.session.put("username", teacher_num);
				this.session.put("name",
						UserTeacherService.getNameByUsername(username));
				this.session.put("teacher", true);
				return "teacher";
			} else {
				boolean login_studentResult = UserService.login_student(
						username, password);
				if (login_studentResult) {
					String student_num = username;
					this.session.put("username", username);
					this.session.put("teacher", false);
					this.session.put("name",
							UserService.getNameByUsername(username));
					return "student";
					// resp.sendRedirect("index_student.java");
				} else {

					HttpServletRequest request = ServletActionContext
							.getRequest();
					request.setAttribute("msg",
							"<script>alert('用户名或者密码错误！');</script>");
					// 转发 不改变url
					// req.getRequestDispatcher("login.jsp").forward(req, resp);
					return "login";
				}
			}
		}else {
//			ServletActionContext.getRequest().getRequestDispatcher("login.jsp").forward(req, resp);
			return "login";
		}
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
