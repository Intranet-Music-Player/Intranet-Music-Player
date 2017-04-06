package com.example.responses;

import com.example.entities.User;


public class UserResponse {
	private User user;
	private boolean success;
	private String message;

	public UserResponse loginCorrect( ){
		UserResponse u = new UserResponse();
		u.message = "LOGIN CORRECT";
		u.success = true;	
		return u;
	}	
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

