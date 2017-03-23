//package com.example;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.example.entities.*;
//import com.example.repository.*;
//import com.example.requsest.UserRequest;
//import com.example.requsest.UserResponse;
//
//@Controller
//@RequestMapping(path="/app")
//public class MainControlle2 {
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@GetMapping(path="/user/add") 
// 
// 	public @ResponseBody String addNewUser( UserRequest user ) {
//		if (userRepository.findByUserlogin(user.getUserlogin()) != null  )	{
//			return new UserResponse().response(0);
//		} else if (userRepository.findByEmail(user.getEmail())!= null){
//			return new UserResponse().response(1);
//		} else {
//			
//			User newUser = new User(user.getName(),user.getUserlogin(),user.getPassword(),user.getEmail());
//			userRepository.save(newUser);
//			return new UserResponse().response(2);
//		}
//	}
//}
