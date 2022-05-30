package com.tweetApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetApp.model.LoginResponse;
import com.tweetApp.model.Register;
import com.tweetApp.repository.RegisterRepoDynamo;

@Service
public class RegisterService {

	@Autowired
	private RegisterRepoDynamo registerRepo;



	public Register addUser(Register register) {
		
		return registerRepo.addUser(register);
	}


	public LoginResponse loginUser(String  emailId , String password) throws Exception{
		Register register = registerRepo.getUser(emailId);
		System.out.println(register);
		if(register == null){
			throw new Exception();
		}
		
		if(register.getPassword().equals(password)) {
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setMessage("login sucessfully");
			loginResponse.setUserId(register.getLoginId());
			return loginResponse;
		}
		else {
			throw new Exception();
		}
		
	}
	 
	public List<Register> getAllUsers() throws Exception{
		List<Register> users = registerRepo.getAll();
		if(users == null){
			throw new Exception();
		}
		return users;
	}
	


}
