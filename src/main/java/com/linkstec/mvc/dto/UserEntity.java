package com.linkstec.mvc.dto;

public class UserEntity {

	private String loginName;
	
	private int id;
	
	private int bindType;
	
	public void setLoginName(String string) {
		this.loginName = string;
	}

	public void setId(int i) {
		this.id = i;
	}

	public void setBindType(int i) {
		this.bindType = i;
	}

	public String getLoginName() {
		return loginName;
	}

	public int getId() {
		return id;
	}

	public int getBindType() {
		return bindType;
	}

	
}
