package com.webprague.StudentSign.pojo;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String college;
	private String phoneNumber;
	private String wechat;
	private String qq;
	private String workPlace;
	private String workCharacter;
	private String mail;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	/** full constructor */
	public User(String username, String password, String name, String sex,
			String college, String phoneNumber, String wechat, String qq,
			String workPlace, String workCharacter,String mail) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.college = college;
		this.phoneNumber = phoneNumber;
		this.wechat = wechat;
		this.qq = qq;
		this.workPlace = workPlace;
		this.workCharacter = workCharacter;
		this.mail = mail;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCollege() {
		return this.college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWechat() {
		return this.wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWorkPlace() {
		return this.workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getWorkCharacter() {
		return this.workCharacter;
	}

	public void setWorkCharacter(String workCharacter) {
		this.workCharacter = workCharacter;
	}
	public String getMail(){
		return this.mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}


}