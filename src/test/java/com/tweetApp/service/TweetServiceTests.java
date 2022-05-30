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
import com.tweetApp.model.Response;
import com.tweetApp.model.Tweet;
import com.tweetApp.repository.TweetRepositoryImpl;

@SpringBootTest
public class TweetServiceTests {

	@MockBean
	TweetRepositoryImpl mocktweetRepositoryImpl;

	@Autowired
	TweetService mockTweetService;

	@Test
	public void testGetAll() {
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.add(tweet);
		Mockito.when(mocktweetRepositoryImpl.getallTweets()).thenReturn(tweetList);
		List<Tweet> result = mockTweetService.getAll();
//		String actualResult = result.get(0).getTweetId();
//		System.out.println(actualResult);
		assertEquals(1, result.size());

	}

	@Test
	public void testgetTweetsofUsers() throws Exception {
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		tweet.setTweet("Hello World");
		tweet.setLike(2);
		tweet.setTimeStamp("123");
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.add(tweet);
		Mockito.when(mocktweetRepositoryImpl.getallTweets()).thenReturn(tweetList);
		List<Tweet> result = mockTweetService.getTweetsofUsers("2");
		assertEquals(1, result.size());

	}

	@Test
	public void testpostTweetByUserDynamo() throws Exception {
		PostRequest postRequest = new PostRequest();
		postRequest.setTweet("Hello World");
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		tweet.setTweet("Hello World");
		tweet.setLike(2);
		tweet.setTimeStamp("123");
		Mockito.when(mocktweetRepositoryImpl.save(Mockito.anyObject())).thenReturn(tweet);
		Response responseResult = mockTweetService.postTweetByUserDynamo("mrinal@gmail.com", postRequest);
		assertEquals("Tweet Posted Successfully", responseResult.getMessage());

	}

	@Test
	public void testgetTweetByUuid() throws Exception {
		PostRequest postRequest = new PostRequest();
		postRequest.setTweet("Hello World");
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		tweet.setTweet("Hello World");
		tweet.setLike(2);
		tweet.setTimeStamp("123");
		Mockito.when(mocktweetRepositoryImpl.getTweetById(Mockito.anyString())).thenReturn(tweet);
		Tweet tweetResult = mockTweetService.getTweetByUuid("1");
		assertEquals("1", tweetResult.getTweetId());

	}

	@Test
	public void testupdateATweet() throws Exception {
		PostRequest postRequest = new PostRequest();
		postRequest.setTweet("Hello World");
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		tweet.setTweet("Hello World");
		tweet.setLike(2);
		tweet.setTimeStamp("123");
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.add(tweet);
		this.mockTweetService.updateATweet("mrinal@gmail.com", "1", postRequest);

	}

	@Test
	public void testdeletebyId() throws Exception {
		PostRequest postRequest = new PostRequest();
		postRequest.setTweet("Hello World");
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		tweet.setTweet("Hello World");
		tweet.setLike(2);
		tweet.setTimeStamp("123");
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.add(tweet);
		this.mockTweetService.deletebyId("2", "1");

	}

	@Test
	public void testlikeTweet() throws Exception {
		PostRequest postRequest = new PostRequest();
		postRequest.setTweet("Hello World");
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		tweet.setTweet("Hello World");
		tweet.setLike(2);
		tweet.setTimeStamp("123");
		Mockito.when(mocktweetRepositoryImpl.getTweetById(Mockito.anyString())).thenReturn(tweet);
		mockTweetService.likeTweet("2", "1");

	}

	@Test
	public void testreplyTweet() throws Exception {
		PostRequest postRequest = new PostRequest();
		postRequest.setTweet("Hello World");
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		tweet.setTweet("Hello World");
		tweet.setLike(2);
		tweet.setTimeStamp("123");
		Mockito.when(mocktweetRepositoryImpl.getTweetById(Mockito.anyString())).thenReturn(tweet);
		mockTweetService.replyTweet("mrinal@gmail.com", "1", postRequest);

	}

}
