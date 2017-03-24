package com.example.response;

import com.example.requsest.UserRequest;

public class UserResponse {
	
	private boolean success;
	private String message;
	private UserRequest user;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public UserRequest getUser() {
		return user;
	}
	public void setUser(UserRequest user) {
		this.user = user;
	}
	public UserResponse loginCorrect(UserRequest user ){
		UserResponse u = new UserResponse();
		u.message = "LOGIN CORRECT";
		u.success = true;
		this.user = user;
		
		return u;
		
		
	}
}
