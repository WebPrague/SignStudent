package com.webprague.StudentSign.action;

import com.opensymphony.xwork2.ActionSupport;
import com.webprague.StudentSign.pojo.SignIn;
import com.webprague.StudentSign.service.SignInService;
import com.webprague.StudentSign.service.UserTeacherService;
import org.apache.struts2.ServletActionContext;

import java.text.ParseException;
import java.util.List;

/**
 * Created by ZhangPeng on 2017/5/17.
 */
public class Student_index extends ActionSupport {

    public String execute() throws Exception{
        if(ServletActionContext.getRequest().getMethod().equals("GET")){

            String student_username = (String) ServletActionContext.getRequest().getSession().getAttribute(
                    "username");
            Integer pagenumber = null;
            try {
                pagenumber = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
            } catch (Exception e) {
                // TODO: handle exception
                pagenumber = 1;
            }
            // req.setAttribute("signusers",
            // SignInService.getAllmessage(student_username));

            int usermount = 10;

            ServletActionContext.getRequest().setAttribute("teachers", UserTeacherService.getAllTeachcers());
            SignIn signIn = SignInService
                    .getNotSignOutSignObjectByUsername((String) (ServletActionContext.getRequest().getSession()
                            .getAttribute("username")));
            ServletActionContext.getRequest().setAttribute("sign", signIn);

            String current_time = ServletActionContext.getRequest().getParameter("date");
            try {
                if (current_time != null) {
                    List<SignIn> list = SignInService
                            .select_time_message(current_time);
                    ServletActionContext.getRequest().setAttribute("sign_list", list);
                    ServletActionContext.getRequest().setAttribute("page_count", 1);
                    ServletActionContext.getRequest().setAttribute("page_number", 1);
                } else {
                    ServletActionContext.getRequest().setAttribute("sign_list", SignInService.getPages(
                            student_username, pagenumber, usermount));
                    Integer pageCount = SignInService.getPageCount(
                            student_username, usermount);
                    ServletActionContext.getRequest().setAttribute("page_count", pageCount);
                    ServletActionContext.getRequest().setAttribute("page_number", pagenumber);



                }

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return "index_student";
        }else{
            return "index_student";
        }

    }


}
