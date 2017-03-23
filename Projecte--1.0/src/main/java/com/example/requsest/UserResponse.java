package com.example.requsest;

public class UserResponse {
	private String response;

	public String response(Integer a){
		
		if ( a == 0){
			return "LOGIN ALREADY EXISTS";
		} else if ( a == 1 ){
			return "EMAIL ALRADY HAS ACCOUNT";
		} else {
			return "NEW USER REGISTRED";
		} 		
	}
}

