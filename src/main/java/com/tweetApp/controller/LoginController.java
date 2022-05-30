package com.tweetApp.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetApp.model.Login;
import com.tweetApp.model.LoginResponse;
import com.tweetApp.model.Register;
import com.tweetApp.model.Response;
import com.tweetApp.model.ResponseStatus;
import com.tweetApp.repository.RegisterRepoDynamo;
import com.tweetApp.service.RegisterService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1.0/tweets")
public class LoginController {

	@Autowired
	RegisterService registerService;
	
	@Autowired
	RegisterRepoDynamo registerRepo;

	private static final Logger log=org.slf4j.LoggerFactory.getLogger(LoginController.class);

	@PostMapping("/register")
	public Response registerUser(@RequestBody Register register) {
		Register reg = null;
		if (register != null && StringUtils.isNotBlank(register.getEmailId())
				&& StringUtils.isNotBlank(register.getPassword())) {
			reg = registerService.addUser(register);
		}
		if (reg != null) {
			return new Response("User Registered Sucessfully!!", ResponseStatus.SUCCESS);
		}
		return new Response("User Registration Unsucessful...Please try again", ResponseStatus.FAILURE);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Login login) throws Exception {
		log.info("User {}",login);
		registerService.loginUser(login.getEmailId(),login.getPassword());
		Register register = registerRepo.getUser(login.getEmailId());
		return ResponseEntity.ok(new LoginResponse(register.getLoginId(), "User login sucessfully"));

	}

	@GetMapping("/users/all")
	public ResponseEntity<List<Register>> getAllUsers() throws Exception {
		List<Register> users = registerService.getAllUsers();
		if(!CollectionUtils.isEmpty(users)) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		return null;

	}
	
//	@GetMapping("/{emailId}")
//	public ResponseEntity<Response> forgetPasswordReset(@PathVariable(value = "emailId") String emailId, @RequestParam String resetToken, @RequestParam String newPass){
//		Response resp = registerService.forgotPasswordUser(resetToken, emailId, newPass);
//		return new ResponseEntity<>(resp,HttpStatus.OK);
//		
//	}
	

}
