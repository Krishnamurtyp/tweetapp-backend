package com.tweetApp.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Login {

	
	@NotNull
	@NotBlank
	@JsonProperty("emailId")
	private String emailId;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,7}$", message = "password must contain atleast on lower and one upper and one number and one special and of length 6")
	private String password;

	
	
}
