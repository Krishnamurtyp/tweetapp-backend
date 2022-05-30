package com.tweetApp.controller;

import java.util.List;

//import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetApp.dto.PostRequest;
import com.tweetApp.exception.TweetLoginException;
//import com.tweetApp.kafka.KafKaProducerService;
import com.tweetApp.model.Response;
import com.tweetApp.model.ResponseStatus;
import com.tweetApp.model.Tweet;
import com.tweetApp.service.TweetService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class TweetController {
	

	@Autowired
	TweetService tweetService;
	
//	private final KafKaProducerService producerService;

//	@Autowired
//	public TweetController( KafKaProducerService producerService)
//	{
//		this.producerService = producerService;
//	}
	
	@GetMapping("/hello")
	public String testHello() {
		return "Hello Mrinal";
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Tweet>> getAllTweets() throws Exception{
		List<Tweet> tweets = tweetService.getAll();
//		this.producerService.sendMessage("All Tweets reterived!" );
		return new ResponseEntity<>(tweets, HttpStatus.OK);
	}
	
	@GetMapping("/byUserId/{userId}")
	public ResponseEntity<List<Tweet>> getTweetsOfUser(@PathVariable(value = "userId") String userId) throws Exception{
		List<Tweet> tweetUser = tweetService.getTweetsofUsers(userId);
//		this.producerService.sendMessage("All users are retrived!" );
		return new ResponseEntity<>(tweetUser, HttpStatus.OK);
	}
	
	@GetMapping("byUuid/{tweetId}")
	public Tweet getTweetByUuid(@PathVariable String tweetId) {
		
//		this.producerService.sendMessage("Retriving Tweet By uuid");
		 Tweet tweet = tweetService.getTweetByUuid(tweetId );
//		 this.producerService.sendMessage(" Tweet Retreived By uuid");
		return tweet;
		
	}
	
	@PostMapping("/{emailId}")
	public Response  postTweetByUser(@PathVariable(value = "emailId") String emailId, @RequestBody PostRequest postRequest){
		this.tweetService.postTweetByUserDynamo(emailId, postRequest);
//		this.producerService.sendMessage("Tweet posted successfully!" );
		return new Response("Tweet Posted Successfully", ResponseStatus.SUCCESS);
	}
	
	@PutMapping("/{userId}/update/{tweetId}")
	public void updateTweet(@PathVariable(value = "userId") String userId, @PathVariable(value = "tweetId") String tweetId, @RequestBody PostRequest postRequest) throws Exception{
//		this.producerService.sendMessage("User updating tweet!" );
		tweetService.updateATweet(userId, tweetId, postRequest);
//		this.producerService.sendMessage("Tweet updated successfully");
		
	}
	
	@DeleteMapping("{userId}/delete/{tweetId}")
	public void deleteTweet(@PathVariable(value = "userId") String userId, @PathVariable(value = "tweetId") String tweetId) throws TweetLoginException {
//		this.producerService.sendMessage("Deleting a tweet");
		tweetService.deletebyId(userId,tweetId);
//		this.producerService.sendMessage("Tweet deleted successfully!");
	}
	
	@PutMapping("/{userId}/like/{tweetId}")
	public void likeTweet(@PathVariable(value = "userId") String userId, @PathVariable(value = "tweetId") String tweetId) throws Exception {
//		this.producerService.sendMessage("Liking a tweet!");
		tweetService.likeTweet(userId, tweetId);
//		this.producerService.sendMessage("Tweet is liked!");
	}
	
	@PostMapping("/{userId}/reply/{tweetId}")
	public void replyTweet(@PathVariable(value = "userId") String userId, @PathVariable(value = "tweetId") String tweetId, @RequestBody PostRequest postRequest) throws Exception{
//		this.producerService.sendMessage("Replying to tweet!");
		tweetService.replyTweet(userId, tweetId, postRequest);
//		this.producerService.sendMessage("Replied to tweet!");
	}
}
