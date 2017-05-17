package com.webprague.StudentSign.pojo;

/**
 * UserTeacher entity. @author MyEclipse Persistence Tools
 */

public class UserTeacher implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String name;
	private String password;
	private String department;
	private String mail;

	// Constructors

	/** default constructor */
	public UserTeacher() {
	}

	/** minimal constructor */
	public UserTeacher(String name) {
		this.name = name;
	}

	/** full constructor */
	public UserTeacher(String username, String name, String password,
			String department,String mail) {
		this.username = username;
		this.name = name;
		this.password = password;
		this.department = department;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}