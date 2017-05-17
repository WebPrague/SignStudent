package com.webprague.StudentSign.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webprague.StudentSign.service.UserService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZhangPeng on 2017/5/17.
 */
public class Student_register extends ActionSupport {

    HttpServletRequest req = ServletActionContext.getRequest();
    @Override
    public String execute() throws Exception {
        if (ServletActionContext.getRequest().getMethod().equals("POST")) {

            String username = req.getParameter("username");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String verifypassword = req.getParameter("verifypassword");
            String sex = req.getParameter("sex");
            String college = req.getParameter("college");
            String phonenumber = req.getParameter("phonenumber");
            String wechat = req.getParameter("wechat");
            String qq = req.getParameter("qq");
            String workplace = req.getParameter("workplace");
            String workcharacter = req.getParameter("workcharacter");
            String mail = req.getParameter("mail");
            int registerResult = UserService.register(username, password, verifypassword, name,  sex,
                    college, phonenumber,  wechat, qq,  workplace,  workcharacter,mail);
            if (registerResult == 0) {
                //注册成功，提示注册已成功，返回登陆界面，进行登陆
                //jump to index
                // 重定向 ，会改变url，丢失原来的rsx3213equest 与 response
                //resp.sendRedirect("login.jsp");
                String msg = "<script>alert('注册成功');window.location.href='login.java';</script>";
                req.setAttribute("msg", msg);
                //req.getRequestDispatcher("register_student.jsp").forward(req, resp);
                return "register_student";
            }else{
                //注册失败，提示用户已存在，无需登录，则返回登陆界面，进行登陆
                String msg = "";
                if (registerResult == 1){
                    msg = "两次密码不一致";
                }
                else{
                    if (registerResult == 2){
                        msg = "用户名已经存在";
                    }
                }
                msg = "<script>alert('" + msg + "');</script>";
                req.setAttribute("msg", msg);
                //转发 不改变url
                //req.getRequestDispatcher("register_student.jsp").forward(req, resp);
                return "register_student";
            }

        }else{

            //req.getRequestDispatcher("register_student.jsp").forward(req, resp);
            return "register_student";
        }

    }
}
