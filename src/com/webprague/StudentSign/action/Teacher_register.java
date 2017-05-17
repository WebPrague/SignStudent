package com.webprague.StudentSign.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webprague.StudentSign.service.UserTeacherService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZhangPeng on 2017/5/17.
 */
public class Teacher_register extends ActionSupport {
    HttpServletRequest req = ServletActionContext.getRequest();
    @Override
    public String execute() throws Exception {
        if (ServletActionContext.getRequest().getMethod().equals("POST")) {


            String username = req.getParameter("username");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String verifypassword = req.getParameter("verifypassword");
            String department = req.getParameter("department");
            String mail = req.getParameter("mail");
            int registerResult = UserTeacherService.register_teacher(username, password, verifypassword, name, department,mail);
            if (registerResult == 0) {
                //注册成功，提示注册已成功，返回登陆界面，进行登陆
                //jump to index
                // 重定向 ，会改变url，丢失原来的rsx3213equest 与 response
                //resp.sendRedirect("login.jsp");
                String msg = "<script>alert('注册成功');window.location.href='login.java';</script>";
                req.setAttribute("msg", msg);
                //req.getRequestDispatcher("register_teacher.jsp").forward(req, resp);

                return "register_teacher";

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
                //req.getRequestDispatcher("register_teacher.jsp").forward(req, resp);
                return "register_teacher";
            }

        }else{

           // req.getRequestDispatcher("register_teacher.jsp").forward(req, resp);
            return "register_teacher";

        }
    }
}
