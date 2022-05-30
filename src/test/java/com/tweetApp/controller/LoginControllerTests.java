package com.tweetApp.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tweetApp.dto.PostRequest;
import com.tweetApp.model.Login;
import com.tweetApp.model.LoginResponse;
import com.tweetApp.model.Register;
import com.tweetApp.model.Response;
import com.tweetApp.model.ResponseStatus;
import com.tweetApp.model.Tweet;
import com.tweetApp.repository.RegisterRepoDynamo;
import com.tweetApp.repository.TweetRepositoryImpl;
import com.tweetApp.service.RegisterService;
import com.tweetApp.service.TweetService;
@AutoConfigureMockMvc
@SpringBootTest
public class LoginControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	RegisterService registerService;
	
	@MockBean
	RegisterRepoDynamo registerRepo;
	
	@Test
	public void testRegisterUser() throws Exception {
		Register registerObj = new Register();
		registerObj.setFirstName("Mrinal");
		registerObj.setLastName("Shubham");
		registerObj.setLoginId("mshubham");
		registerObj.setPassword("mrinal@123");
		registerObj.setConfirmPassword("mrinal@123");
		registerObj.setEmailId("mrinal@gmail.com");
		registerObj.setContactNumber("7992318095");
		registerObj.setResetAnswer("answer");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter objWriter = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = objWriter.writeValueAsString(registerObj);
		this.mockMvc.perform(post("/api/v1.0/tweets/register")
				.contentType("application/json").content(requestJson)).andExpect(status().isOk());
	}
	
	@Test
	public void testRegisterUser_Negative() throws Exception {
		Register registerObj = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter objWriter = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = objWriter.writeValueAsString(registerObj);
		this.mockMvc.perform(post("/api/v1.0/tweets/register")
				.contentType("application/json").content(requestJson)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testLogin() throws Exception {
		Login login = new Login();
		login.setEmailId("mrinal@gmail.com");
		login.setPassword("mrinal@123");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter objWriter = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = objWriter.writeValueAsString(login);
		LoginResponse loginResp = new LoginResponse();
		loginResp.setUserId("2");
		loginResp.setMessage("Logged In");
		Register registerObj = new Register();
		registerObj.setFirstName("Mrinal");
		registerObj.setLastName("Shubham");
		registerObj.setLoginId("mshubham");
		registerObj.setPassword("mrinal@123");
		registerObj.setConfirmPassword("mrinal@123");
		registerObj.setEmailId("mrinal@gmail.com");
		registerObj.setContactNumber("7992318095");
		registerObj.setResetAnswer("answer");
		Mockito.when(registerService.loginUser(Mockito.anyString(), Mockito.anyString())).thenReturn(loginResp);
		Mockito.when(registerRepo.getUser(Mockito.anyString())).thenReturn(registerObj);
		this.mockMvc.perform(post("/api/v1.0/tweets/login")
				.contentType("application/json").content(requestJson)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
		this.mockMvc.perform(get("/api/v1.0/tweets//users/all")).andExpect(status().isOk());
	}
	

}
