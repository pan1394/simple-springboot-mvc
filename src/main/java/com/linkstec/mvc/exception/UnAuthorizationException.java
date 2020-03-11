package com.linkstec.mvc.exception;

public class UnAuthorizationException extends MvcException{
 
	private static final long serialVersionUID = 11233243L;

	public UnAuthorizationException(String msg) {
		super(msg);
	}
}
