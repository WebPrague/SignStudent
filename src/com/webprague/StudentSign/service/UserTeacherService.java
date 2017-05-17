package com.webprague.StudentSign.service;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.webprague.StudentSign.DAO.UserTeacherDAO;
import com.webprague.StudentSign.pojo.User;
import com.webprague.StudentSign.pojo.UserTeacher;

public class UserTeacherService {
	private static UserTeacherDAO userteacherDAO = new UserTeacherDAO();
	public static boolean login_teacher(String username,String password){
		UserTeacher userteacher = new UserTeacher();
		userteacher.setUsername(username);
		userteacher.setPassword(password);
		List<UserTeacher>list = userteacherDAO.findByExample(userteacher);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	//查找有邮箱的用户是否存在
	public static boolean forgetPassword(String username,String mail){
		UserTeacher user = new UserTeacher();
		user.setUsername(username);
		user.setMail(mail);
		List<User>list = userteacherDAO.findByExample(user);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	//通过用户名查找密码
	public static String getUserPasswd(String username){
		List<UserTeacher>users = userteacherDAO.findByUsername(username);
		if (users.size() == 0) {
			return "";
		}
		UserTeacher user = users.get(0);
		return user.getPassword();
	}

	/**
	 * 0: 注册成功
	 * 1：  密码不一致
	 * 2：  用户名已在
	 * */
	public static int register_teacher(String username, String password,String rePassword ,String name, String department,String mail) {
		// TODO Auto-generated method stub
		if(!password.equals(rePassword)){
			return 1;
		}
		if(userteacherDAO.findByUsername(username).size() > 0){
			return 2;
		}
//		User nUser = new User();
//		nUser.setUsername(username);
//		if (userDAO.findByExample(nUser).size() > 0) {
//			return 2;
//		}

		UserTeacher userteacher = new UserTeacher();
		userteacher.setUsername(username);
		userteacher.setPassword(password);
		userteacher.setName(name);
		userteacher.setDepartment(department);
		userteacher.setMail(mail);
		userteacherDAO.save(userteacher);
		return 0;
	}

	public static String getNameByUsername(String username){
		List<UserTeacher>users = userteacherDAO.findByUsername(username);
		if (users.size() == 0) {
			return "";
		}
		UserTeacher user = users.get(0);
		return user.getName();
	}

	public static List<UserTeacher> getAllTeachcers() {
		return userteacherDAO.findAll();
	}
	public static UserTeacher teacher_info(String username){
		UserTeacher user_teacher = new UserTeacher();
		user_teacher.setUsername(username);
		List<UserTeacher>userList = userteacherDAO.findByExample(user_teacher);
		return userList.get(0);

	}
	public static int change_teacher_info(String username,String name,String password, String department,String mail){
		UserTeacher change_user_info = new UserTeacher();
		change_user_info.setUsername(username);
		List<UserTeacher>list = userteacherDAO.findByExample(change_user_info);
		change_user_info = list.get(0);
		if(password.equals("")){

		}else {
			change_user_info.setPassword(password);
		}
		change_user_info.setName(name);;
		change_user_info.setDepartment(department);
		userteacherDAO.save(change_user_info);
		return 0;
	}
}
