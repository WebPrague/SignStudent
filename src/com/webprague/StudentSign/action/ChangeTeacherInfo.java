package com.webprague.StudentSign.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webprague.StudentSign.pojo.UserTeacher;
import com.webprague.StudentSign.service.UserTeacherService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZhangPeng on 2017/5/17.
 */
public class ChangeTeacherInfo extends ActionSupport {

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (ServletActionContext.getRequest().getMethod().equals("POST")){

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
                //response.sendRedirect("index_teacher.java");
                return "index_teacher";
            }
            return "";

        }else{

            UserTeacher user_teacher = UserTeacherService
                    .teacher_info((String) (request.getSession()
                            .getAttribute("username")));
            request.setAttribute("user_teacher", user_teacher);
            //request.getRequestDispatcher("change_teacher_info.jsp").forward(request, response);
            return "change_teacher_info";
        }
    }
}
