package com.tweetApp.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetApp.dto.PostRequest;
import com.tweetApp.exception.TweetLoginException;
import com.tweetApp.model.Response;
import com.tweetApp.model.ResponseStatus;
import com.tweetApp.model.Tweet;
import com.tweetApp.repository.TweetRepositoryImpl;

@Service
public class TweetService {

	@Autowired
	private TweetRepositoryImpl tweetRepositoryImpl;

	public List<Tweet> getTweetsofUsers(String userId) throws Exception {
		List<Tweet> userTweets = tweetRepositoryImpl.getallTweets();
		List<Tweet> collect = userTweets.stream().filter(tweet -> null != tweet && tweet.getUserId().equalsIgnoreCase(userId))
				.collect(Collectors.toList());
		if (collect == null) {
			throw new Exception();
		}
		return collect;
	}
	
	public List<Tweet> getAll() {
		return tweetRepositoryImpl.getallTweets();
	}
	

	public void updateATweet(String emailId, String tweetId, PostRequest postRequest) throws Exception {
		if (postRequest != null) {
			tweetRepositoryImpl.updateTweet(emailId, tweetId, postRequest);
		} else {
			throw new Exception("Updating a tweet was unsucessfull..try again");
		}
	}

	public void deletebyId(String userId, String tweetId) throws TweetLoginException {
		if (tweetId != null) {
			tweetRepositoryImpl.delete(tweetId);
		} else {
			throw new TweetLoginException("Delete a tweet was unsucessfull..try again");
		}

	}

	public void likeTweet(String userId, String tweetId) throws Exception {
		int like = 0;
		Tweet tweet = tweetRepositoryImpl.getTweetById(tweetId);
		like = tweet.getLike();
		tweetRepositoryImpl.likeAtweet(tweetId, like);

	}

	public void replyTweet(String emailId, String tweetId, PostRequest postRequest) {
		if (tweetId != null) {

			Tweet mainTweet = tweetRepositoryImpl.getTweetById(tweetId);
			Tweet replytweet = new Tweet();
			UUID uuid = UUID.randomUUID();
			LocalDate lt = LocalDate.now();
			replytweet.setTweetId(uuid.toString());
			replytweet.setUserId(emailId);
			replytweet.setTweet(postRequest.getTweet());
			replytweet.setLike(0);
			replytweet.setTimeStamp(lt.toString());

			List<Tweet> repliedtweet = new ArrayList<>();

			List<Tweet> alrdyreply = mainTweet.getReply();
			if (alrdyreply != null) {
				alrdyreply.add(replytweet);
				tweetRepositoryImpl.replyTweet(alrdyreply, tweetId);

			} else {
				repliedtweet.add(replytweet);
				tweetRepositoryImpl.replyTweet(repliedtweet, tweetId);
			}
		}
	}

	public Tweet getTweetByUuid(String tweetId) {
		return tweetRepositoryImpl.getTweetById(tweetId);		
	}

	public Response postTweetByUserDynamo(String emailId, PostRequest postRequest) {
		UUID uuid = UUID.randomUUID();
		Tweet tweet2 = new Tweet();
		tweet2.setTweet(postRequest.getTweet());
		tweet2.setTweetId(uuid.toString());
		tweet2.setUserId(emailId);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tweet2.setTimeStamp(timestamp.toString());
		tweetRepositoryImpl.save(tweet2);
		return new Response("Tweet Posted Successfully", ResponseStatus.SUCCESS);
	}
}
