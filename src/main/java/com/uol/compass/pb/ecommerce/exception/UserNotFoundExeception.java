package com.uol.compass.pb.ecommerce.exception;

public class UserNotFoundExeception extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundExeception(String msg) {
		super(msg);
	}
}
