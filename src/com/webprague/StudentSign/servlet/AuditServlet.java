package com.webprague.StudentSign.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webprague.StudentSign.service.SignInService;



@WebServlet("/audit")
public class AuditServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		String action = req.getParameter("action");
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			SignInService.audit(id,action);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String referer=req.getHeader("referer");
		resp.sendRedirect(referer);
	}
}
