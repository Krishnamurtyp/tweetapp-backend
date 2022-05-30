package com.tweetApp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamoDBTable(tableName = "register")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Register {

	@NotNull
	@NotBlank
	@DynamoDBAttribute
	private String firstName;
	
	@NotNull
	@NotBlank
	@DynamoDBAttribute
	private String lastName;
	
	@NotNull
	@NotBlank
	@DynamoDBAttribute
	private String loginId;
	
	@NotNull
	@NotBlank(message = "New password is mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,7}$", message = "password must contain atleast on lower and one upper and one number and one special and of length 6")
	@DynamoDBAttribute
	private String password;
	
	@NotNull
	@NotBlank(message = "New password is mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,7}$", message = "password must contain atleast on lower and one upper and one number and one special and of length 6")
	@DynamoDBAttribute
	private String confirmPassword;
	
	@NotNull
	@NotBlank
	@Email
	@DynamoDBHashKey
	private String emailId;
	
//	@NotNull
//	@NotBlank
//	private String dob;
	
	
	
	@NotNull
	@Pattern(regexp = "^[0-9]{10}",message = "contact Number should be 10 digit Number")
	@DynamoDBAttribute
	private String contactNumber;
	
	
	
	@NotNull
	@NotBlank
	@DynamoDBAttribute
	private String resetAnswer;

	@Override
	public String toString() {
		return "Register [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", emailId=" + emailId + ", contactNumber=" + contactNumber
				+ ", loginId=" + loginId + ", resetAnswer=" + resetAnswer + "]";
	}
	
	

}