package com.linkstec.mvc.dto;

public class UserDto {

	private String loginName;
	
	private String id;
	
	private String pwd;
 
	public void setLoginName(String string) {
		this.loginName = string;
	}
 
	public String getLoginName() {
		return loginName;
	}
 
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
 
	
		
	
}
