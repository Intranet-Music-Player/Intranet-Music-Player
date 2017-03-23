package com.example.requsest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {
	
	private String name;
	private String userlogin;
	private String password;
	private String email;
	
	@JsonCreator
	public UserRequest (@JsonProperty("name") String name,
						@JsonProperty("userlogin") String userlogin,
						@JsonProperty("password") String password,
						@JsonProperty("email") String email){
		super();
		this.name=name;
		this.userlogin=userlogin;
		this.password=password;
		this.email=email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
