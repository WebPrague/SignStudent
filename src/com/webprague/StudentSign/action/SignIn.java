package com.webprague.StudentSign.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webprague.StudentSign.service.SignInService;
import com.webprague.StudentSign.service.UserService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by ZhangPeng on 2017/5/17.
 */
public class SignIn extends ActionSupport {

    HttpServletRequest req = ServletActionContext.getRequest();

    @Override
    public String execute() throws Exception {

        if (ServletActionContext.getRequest().getMethod().equals("POST")) {

            String teacher_id = req.getParameter("teacher_username");
            String student_username = (String)req.getSession().getAttribute("username");
            String student_name = UserService.getNameByUsername(student_username);
            Timestamp signin_time = new Timestamp(System.currentTimeMillis());
            SignInService.add_signin(student_username,student_name,signin_time,teacher_id);
            //req.getRequestDispatcher("sign_in.jsp").forward(req, resp);

            req.setAttribute("msg", "签到成功");
            req.setAttribute("location", "index_student.java");
            //req.getRequestDispatcher("jump.jsp").forward(req, resp);
            //resp.sendRedirect("index_student.java");

            return "jump";
        }
        return "";

    }
}
