package com.example;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.entities.User;
import com.example.repository.UserRepository;
import com.example.requsest.UserRequest;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class IntranetMusicPlayerApplicationTests extends ApplicationContextTestUtils {
//	
//	@Mock 
//	private UserRepository userRepository;
//	
//	@InjectMocks
//	public MainControlle2 main = new MainControlle2();
//
//	@Test
//	public void testUserRegistration(){
//		UserRequest user = new UserRequest("Damia A","damia","eldamia","damia@gmail.com");
//		System.err.println(main.addNewUser(user));		
//	}
//	
	@Test
	public void testSampleService() {
	    assertTrue(true);
	}
	
}
