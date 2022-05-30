package com.tweetApp.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "tweet")
public class Tweet {

	@DynamoDBHashKey
	private String tweetId;
	
	@DynamoDBAttribute
	private String userId;
	
	@NotNull
	@NotBlank(message = "tweet Cannot be Blank.please enter tweet ")
	@Pattern(regexp = "^[a-zA-Z0-9]")
	@Max(value = 144)
	@DynamoDBAttribute
	private String tweet;
	
	@DynamoDBAttribute
	private String timeStamp;
	
	@DynamoDBAttribute
	private int like;
	
	@DynamoDBAttribute
	private List<Tweet> reply;

	public Tweet() {
		super();
	}

	public Tweet(String tweetId, String userId, String tweet, String timestamp, int like, List<Tweet> reply) {
		super();
		this.tweetId = tweetId;
		this.userId = userId;
		this.tweet = tweet;
		this.timeStamp = timestamp;
		this.like = like;
		this.reply = reply;
	}
	
	

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public List<Tweet> getReply() {
		return reply;
	}

	public void setReply(List<Tweet> reply) {
		this.reply = reply;
	}

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
