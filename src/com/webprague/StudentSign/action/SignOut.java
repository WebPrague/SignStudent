package com.webprague.StudentSign.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webprague.StudentSign.service.SignInService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZhangPeng on 2017/5/17.
 */
public class SignOut extends ActionSupport {

    HttpServletRequest request = ServletActionContext.getRequest();

    @Override
    public String execute() throws Exception {
        if (ServletActionContext.getRequest().getMethod().equals("POST")) {

            String remark = request.getParameter("remark");
            String student_username = (String)request.getSession().getAttribute("username");

            SignInService.add_signout(student_username,remark);
            //response.sendRedirect("index_student.java");
            request.setAttribute("msg", "签离成功");
            request.setAttribute("location", "index_student.java");
            //request.getRequestDispatcher("jump.jsp").forward(request, response);
            return "jump";

        }else{
            return "";
        }
    }
}
