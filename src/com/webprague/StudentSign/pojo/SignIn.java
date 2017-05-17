package com.webprague.StudentSign.pojo;

import java.sql.Timestamp;

/**
 * SignIn entity. @author MyEclipse Persistence Tools
 */

public class SignIn implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String name;
	private Timestamp signinTime;
	private Timestamp signoutTime;
	private String remark;
	private String teacherUsername;
	private String status;

	// Constructors

	/** default constructor */
	public SignIn() {
	}

	/** minimal constructor */
	public SignIn(String name, String teacherUsername) {
		this.name = name;
		this.teacherUsername = teacherUsername;
	}

	/** full constructor */
	public SignIn(String username, String name, Timestamp signinTime,
			Timestamp signoutTime, String remark, String teacherUsername,
			String status) {
		this.username = username;
		this.name = name;
		this.signinTime = signinTime;
		this.signoutTime = signoutTime;
		this.remark = remark;
		this.teacherUsername = teacherUsername;
		this.status = status;
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

	public Timestamp getSigninTime() {
		return this.signinTime;
	}

	public void setSigninTime(Timestamp signinTime) {
		this.signinTime = signinTime;
	}

	public Timestamp getSignoutTime() {
		return this.signoutTime;
	}

	public void setSignoutTime(Timestamp signoutTime) {
		this.signoutTime = signoutTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTeacherUsername() {
		return this.teacherUsername;
	}

	public void setTeacherUsername(String teacherUsername) {
		this.teacherUsername = teacherUsername;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}