package com.webprague.StudentSign.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.webprague.StudentSign.DAO.SignInDAO;
import com.webprague.StudentSign.pojo.SignIn;
import com.webprague.StudentSign.pojo.User;
public class SignInService {
	//private static SignInDAO signinDAO = new SignInDAO();

	public static int add_signin(String username,String name,Timestamp signin_time,String teacher_uasername) {
		SignIn signin = new SignIn();
		signin.setUsername(username);
		signin.setName(name);
		signin.setSigninTime(signin_time);
		signin.setSignoutTime(new Timestamp(0));
		signin.setTeacherUsername(teacher_uasername);
		new SignInDAO().save(signin);
		return 0;
	}


	public static SignIn getNotSignOutSignObjectByUsername(String username) {
		SignIn signIn = new SignIn();
		signIn.setUsername(username);
		signIn.setSignoutTime(new Timestamp(0));
		List<SignIn>signIns = new SignInDAO().findByExample(signIn);
		if (signIns.size() == 0) {
			return null;
		}
		return signIns.get(0);
	}

	public static int add_signout(String username , String remark){

		SignIn signout = new SignIn();
		signout.setUsername(username);
		signout.setSignoutTime(new Timestamp(0));
		List<SignIn>signs = new SignInDAO().findByExample(signout);
		signout = signs.get(0);
		signout.setRemark(remark);
		signout.setSignoutTime(new Timestamp(System.currentTimeMillis()));
		signout.setStatus("未审核");
		new SignInDAO().save(signout);
		return 0;
	}
	public static List<SignIn> getAllmessage(String username) {
		SignIn signuser = new SignIn();
		signuser.setUsername(username);
		//List<SignIn>signIns = signinDAO.findByExample(signuser);
		return new SignInDAO().findByExample(signuser);
	}
	//获得学生界面的分页
	public static List<SignIn> getPages(String username,int pagenumber,int usermount){
		SignIn signusers  = new SignIn();
		signusers.setUsername(username);
		List<SignIn>pageuser = new SignInDAO().findByExample(signusers);
		List<SignIn>newList = new ArrayList<SignIn>();
		for (int i = 0; i < usermount; i++) {
			try {
				newList.add(pageuser.get((pagenumber-1)*usermount+i));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return newList;
	}
	//获得教师页面的分页
	public static List<SignIn> getTeacherPages(String username,int pagenumber,int usermount){
		SignIn signIn = new SignIn();
		signIn.setTeacherUsername(username);
		List<SignIn>teacherPageUser = new SignInDAO().findByExample(signIn);
		List<SignIn>newlist = new ArrayList<SignIn>();
		for(int i=0;i<usermount;i++){
			try {
				newlist.add(teacherPageUser.get((pagenumber-1)*usermount+i));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return newlist;
	}


	public static Integer getPageCount(String username,int usermount){
		List<SignIn>list = new SignInDAO().findByUsername(username);
		return list.size() / usermount + 1;
	}

	public static Integer getTeacherPageCount(String username,int usermount){
		List<SignIn>list = new SignInDAO().findByTeacherUsername(username);
		return list.size() / usermount + 1;
	}
	//学生用户查询指定日期
	public static List<SignIn> select_time_message(String date) throws ParseException{
		SignIn select_time_person = new SignIn();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date2;
		date2 = simpleDateFormat.parse(date);
		//select_time_person.setSigninTime(new Timestamp(date2.getTime()+ 24 * 60 * 60 *1000));
		List<SignIn>all = new SignInDAO().findAll();

		List<SignIn>newlist = new ArrayList<SignIn>();
//			for(SignIn signIn :all){
//				if(signIn.getSigninTime().getTime()<=date2.getTime()&&date2.getTime()<=signIn.getSigninTime().getTime()+ 24 * 60 * 60 *1000){
//					newlist.add(signIn);
//				}
//			}

		for(SignIn signIn :  all){
			if (signIn.getSigninTime().getTime()>=date2.getTime() && signIn.getSigninTime().getTime() <= (date2.getTime() + 24 * 60 * 60 * 1000 )) {
				newlist.add(signIn);
			}
		}
		return newlist;
	}
	//教师用户查询指定日期
	public static List<SignIn> select_time_TeacherMessage(String username,String date) throws ParseException{
		SignIn select_time_person = new SignIn();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date2;
		date2 = simpleDateFormat.parse(date);
		//select_time_person.setSigninTime(new Timestamp(date2.getTime()+ 24 * 60 * 60 *1000));
		select_time_person.setTeacherUsername(username);
		List<SignIn>all = new SignInDAO().findByExample(select_time_person);

		List<SignIn>newlist = new ArrayList<SignIn>();
//			for(SignIn signIn :all){
//				if(signIn.getSigninTime().getTime()<=date2.getTime()&&date2.getTime()<=signIn.getSigninTime().getTime()+ 24 * 60 * 60 *1000){
//					newlist.add(signIn);
//				}
//			}

		for(SignIn signIn :  all){
			if (signIn.getSigninTime().getTime()>=date2.getTime() && signIn.getSigninTime().getTime() <= (date2.getTime() + 24 * 60 * 60 * 1000 )) {
				newlist.add(signIn);
			}
		}
		return newlist;
	}
	//教师用户查询特定学号
	public static List<SignIn> select_stu_num(String teacher_name,String stu_num){
		SignIn stu_num_person = new SignIn();
		stu_num_person.setTeacherUsername(teacher_name);
		stu_num_person.setUsername(stu_num);
		List<SignIn>newlist = new SignInDAO().findByExample(stu_num_person);
		return newlist;
	}


	//教师页面获取列表信息
	public static List<SignIn> getSignInTable(String teacher_name){
		SignIn signInUser = new SignIn();
		signInUser.setTeacherUsername(teacher_name);
		List<SignIn>userList = new SignInDAO().findByExample(signInUser);
		List<SignIn>newlist = new ArrayList<SignIn>();
		for(SignIn signin:userList){
			if(signin.getSignoutTime()!=null){
				newlist.add(signin);
			}
		}
		return newlist;
	}


	public static void audit(Integer id, String action) {
		SignInDAO signInDAO = new SignInDAO();
		if (action.contains("success")) {

			SignIn signIn = signInDAO.findById(id);
			signIn.setStatus("审核通过");
			signInDAO.save(signIn);
		}else {
			SignIn signIn = signInDAO.findById(id);
			signIn.setStatus("审核未通过");
			signInDAO.save(signIn);
		}
	}
}
