package com.example.requsest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {
	
	private String username;
	private String userlogin;
	private String password;
	private String email;
	
	@JsonCreator
	public UserRequest (@JsonProperty("username") String username,
						@JsonProperty("userlogin") String userlogin,
						@JsonProperty("password") String password,
						@JsonProperty("email") String email){
		super();
		this.username=username;
		this.userlogin=userlogin;
		this.password=password;
		this.email=email;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserlogin() {
		return userlogin;
	}
	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
