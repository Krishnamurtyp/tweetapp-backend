package com.tweetApp.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tweetApp.dto.PostRequest;
import com.tweetApp.model.LoginResponse;
import com.tweetApp.model.Register;
import com.tweetApp.model.Tweet;
import com.tweetApp.repository.RegisterRepoDynamo;
import com.tweetApp.repository.TweetRepositoryImpl;


@SpringBootTest
public class RegisterServiceTests {
	
	@MockBean
	RegisterRepoDynamo mockRegisterRepo;
	
	@Autowired
	RegisterService mockRegisterService;
	
	@Test
	public void testloginUser() throws Exception {
		Register registerObj = new Register();
		registerObj.setFirstName("Mrinal");
		registerObj.setLastName("Shubham");
		registerObj.setLoginId("mshubham");
		registerObj.setPassword("mrinal@123");
		registerObj.setConfirmPassword("mrinal@123");
		registerObj.setEmailId("mrinal@gmail.com");
		registerObj.setContactNumber("7992318095");
		registerObj.setResetAnswer("answer");
		Mockito.when(mockRegisterRepo.getUser(Mockito.anyString())).thenReturn(registerObj);
		LoginResponse loginResponse = mockRegisterService.loginUser("mrinal@gmail.com","mrinal@123");
		assertEquals("login sucessfully",loginResponse.getMessage());
	}
	
	@Test
	public void testgetAllUsers() throws Exception {
		Register registerObj = new Register();
		registerObj.setFirstName("Mrinal");
		registerObj.setLastName("Shubham");
		registerObj.setLoginId("mshubham");
		registerObj.setPassword("mrinal@123");
		registerObj.setConfirmPassword("mrinal@123");
		registerObj.setEmailId("mrinal@gmail.com");
		registerObj.setContactNumber("7992318095");
		registerObj.setResetAnswer("answer");
		List<Register> users = new ArrayList<Register>();
		users.add(registerObj);
		Mockito.when(mockRegisterRepo.getAll()).thenReturn(users);
		List<Register> registeredUsers = mockRegisterService.getAllUsers();
		assertEquals(1, registeredUsers.size());
	}

}
