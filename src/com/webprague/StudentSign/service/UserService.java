package com.webprague.StudentSign.service;

import java.sql.Timestamp;
import java.util.List;

import com.webprague.StudentSign.DAO.UserDAO;
import com.webprague.StudentSign.pojo.SignIn;
import com.webprague.StudentSign.pojo.User;
import com.webprague.StudentSign.pojo.UserTeacher;

public class UserService {
	private static UserDAO userDAO = new UserDAO();



	public static boolean login_student(String username,String password){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		List<User>list = userDAO.findByExample(user);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	//查找有邮箱的用户是否存在
	public static boolean forgetPassword(String username,String mail){
		User user = new User();
		user.setUsername(username);
		user.setMail(mail);
		List<User>list = userDAO.findByExample(user);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	//通过用户名查找密码
	public static String getUserPasswd(String username){
		List<User>users = userDAO.findByUsername(username);
		if (users.size() == 0) {
			return "";
		}
		User user = users.get(0);
		return user.getPassword();
	}

	/**
	 * 0: 注册成功
	 * 1：  密码不一致
	 * 2：  用户名已在
	 * */
	public static int register(String username, String password,String rePassword ,String name, String sex,
							   String college,String phonenumber, String wechat,String qq, String workplace, String workcharacter,String mail) {
		// TODO Auto-generated method stub
		if(!password.equals(rePassword)){
			return 1;
		}
		if(userDAO.findByUsername(username).size() > 0){
			return 2;
		}
//		User nUser = new User();
//		nUser.setUsername(username);
//		if (userDAO.findByExample(nUser).size() > 0) {
//			return 2;
//		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setSex(sex);
		user.setCollege(college);
		user.setPhoneNumber(phonenumber);
		user.setWechat(wechat);
		user.setQq(qq);
		user.setWorkPlace(workplace);
		user.setWorkCharacter(workcharacter);
		user.setMail(mail);
		userDAO.save(user);
		return 0;
	}

	public static String getNameByUsername(String username){
		List<User>users = userDAO.findByUsername(username);
		if (users.size() == 0) {
			return "";
		}
		User user = users.get(0);
		return user.getName();
	}

	public static int change_student_info(String username,String sex,String college,String phonenumber,String wechat,String password, String qq,String workplace,String workcharacter,String mail){
		User change_user_info = new User();
		change_user_info.setUsername(username);
		List<User>list = userDAO.findByExample(change_user_info);
		change_user_info = list.get(0);
		if(password.equals("")){

		}else {
			change_user_info.setPassword(password);
		}

		change_user_info.setSex(sex);
		change_user_info.setCollege(college);
		change_user_info.setPhoneNumber(phonenumber);
		change_user_info.setWechat(wechat);
		change_user_info.setQq(qq);
		change_user_info.setWorkPlace(workplace);
		change_user_info.setWorkCharacter(workcharacter);
		change_user_info.setMail(mail);
		userDAO.save(change_user_info);
		return 0;
	}

	public static User student_info(String username){
		User user = new User();
		user.setUsername(username);
		List<User>userList = userDAO.findByExample(user);
		return userList.get(0);

	}

}
