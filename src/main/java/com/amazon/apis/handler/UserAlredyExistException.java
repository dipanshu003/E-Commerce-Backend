package com.amazon.apis.handler;

public class UserAlredyExistException extends RuntimeException{

	public UserAlredyExistException(String msg) {
		super(msg);
	}
	
}
