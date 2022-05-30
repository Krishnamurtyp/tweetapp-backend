package com.tweetApp.controller;

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
import com.tweetApp.model.Response;
import com.tweetApp.model.ResponseStatus;
import com.tweetApp.model.Tweet;
import com.tweetApp.repository.TweetRepositoryImpl;
import com.tweetApp.service.TweetService;

@AutoConfigureMockMvc
@SpringBootTest
public class TweetControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TweetService tweetService;

	@MockBean
	private TweetRepositoryImpl tweetRepositoryImpl;
	
	@Test
	public void testHello() throws Exception {
		mockMvc.perform(get("/api/v1.0/tweets/hello")).andExpect(status().isOk());
	}

	@Test
	public void testGetAllTweets() throws Exception {
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.add(tweet);
		Mockito.when(tweetService.getAll()).thenReturn(tweetList);
		mockMvc.perform(get("/api/v1.0/tweets/all")).andExpect(status().isOk());
	}

	@Test
	public void testGetTweetsOfUser() throws Exception {
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.add(tweet);
		Mockito.when(tweetService.getTweetsofUsers(Mockito.anyString())).thenReturn(tweetList);
		this.mockMvc.perform(get("/api/v1.0/tweets/byUserId/2").param("userId", "2")).andExpect(status().isOk());
	}

	@Test
	public void testGetTweetByUuid() throws Exception {
		Tweet tweet = new Tweet();
		tweet.setTweetId("1");
		tweet.setUserId("2");
		List<Tweet> tweetList = new ArrayList<Tweet>();
		tweetList.add(tweet);
		Mockito.when(tweetService.getTweetByUuid(Mockito.anyString())).thenReturn(tweet);
		this.mockMvc.perform(get("/api/v1.0/tweets/byUuid/1").param("tweetId", "1")).andExpect(status().isOk());
	}

	@Test
	public void testPostTweetByUser() throws Exception {

		PostRequest postRequest = new PostRequest();
		postRequest.setTweet("Hello World");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter objWriter = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = objWriter.writeValueAsString(postRequest);
		Mockito.when(tweetService.postTweetByUserDynamo(Mockito.anyString(), Mockito.anyObject()))
				.thenReturn(new Response());
		this.mockMvc.perform(post("/api/v1.0/tweets/mrinal@gmail.com").param("emailId", "mrinal@gmail.com")
				.contentType("application/json").content(requestJson)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteTweet() throws Exception {
//		Mockito.when(tweetService.deletebyId(Mockito.anyString(), Mockito.anyString()));
		this.mockMvc.perform(delete("/api/v1.0/tweets/2/delete/1").param("userId", "2").param("tweetId", "1"))
				.andExpect(status().isOk());
	}

	@Test
	public void testLikeTweet() throws Exception {
		this.mockMvc.perform(put("/api/v1.0/tweets/2/like/1").param("userId", "2").param("tweetId", "1"))
				.andExpect(status().isOk());
	}

	@Test
	public void testReplyTweet() throws Exception {
		PostRequest postRequest = new PostRequest();
		postRequest.setTweet("Hello World");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter objWriter = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = objWriter.writeValueAsString(postRequest);
		this.mockMvc.perform(post("/api/v1.0/tweets/2/reply/1").param("userId", "2").param("tweetId", "1")
				.contentType("application/json").content(requestJson)).andExpect(status().isOk());
	}

	@Test
	public void testUpdateTweet() throws Exception {
		PostRequest postRequest = new PostRequest();
		postRequest.setTweet("Hello World");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter objWriter = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = objWriter.writeValueAsString(postRequest);
		this.mockMvc.perform(put("/api/v1.0/tweets/2/update/1").param("userId", "2").param("tweetId", "1")
				.contentType("application/json").content(requestJson)).andExpect(status().isOk());
	}

}
