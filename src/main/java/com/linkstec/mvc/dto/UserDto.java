package com.linkstec.mvc.dto;

public class UserDto {

	private String loginName;
	
	private String id;
	
	private String pwd;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "UserDto{" +
				"loginName='" + loginName + '\'' +
				", id='" + id + '\'' +
				", pwd='" + pwd + '\'' +
				'}';
	}
}
