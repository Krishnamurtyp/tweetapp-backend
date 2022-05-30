package com.tweetApp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetApp.model.Register;

@Service
public class RegisterRepoDynamo {

	
	@Autowired
	DynamoDBMapper dbMapper;
	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	
	public Register addUser(Register register) {
		dbMapper.save(register);
		return register;
	}
	
	public Register getUser(String emailId) {
		return dbMapper.load(Register.class,emailId);
	}
	
	public List<Register> getAll(){
		return  dbMapper.scan(Register.class, scanExpression);
	}
}
