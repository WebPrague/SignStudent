package com.webprague.StudentSign.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webprague.StudentSign.pojo.User;
import com.webprague.StudentSign.service.UserService;
import com.webprague.StudentSign.service.UserTeacherService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ZhangPeng on 2017/5/17.
 */
public class ChangeStudentInfo extends ActionSupport {

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (ServletActionContext.getRequest().getMethod().equals("POST")){

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
                // response.sendRedirect("index_student.java");
                return "index_student";
            }

            return  "";

        }else{
            User user = UserService
                    .student_info((String) (request.getSession()
                            .getAttribute("username")));
            request.setAttribute("user", user);
            //request.getRequestDispatcher("change_student_info.jsp").forward(request, response);
            return "change_student_info";
        }
    }
}
