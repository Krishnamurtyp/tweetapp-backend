package com.tweetApp.repository;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.tweetApp.dto.PostRequest;
import com.tweetApp.model.Tweet;

@Service
public class TweetRepositoryImpl {

	@Autowired
	DynamoDBMapper dbMapper;

	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	private static Logger logger = LoggerFactory.getLogger(TweetRepositoryImpl.class);

	public void updateTweet(String userId, String tweetId, PostRequest postRequest) {

		Tweet tweetById = getTweetById(tweetId);
		if (null != postRequest && StringUtils.isNotBlank(postRequest.getTweet())) {
			tweetById.setTweet(postRequest.getTweet());
		}
		update(tweetId, tweetById);

		logger.info("tweet is updated ");

	}

	public void likeAtweet(String tweetId, int likeCount) {
		likeCount = likeCount + 1;
		Tweet tweetById = getTweetById(tweetId);
		tweetById.setLike(likeCount);
		update(tweetId, tweetById);

		logger.info("updated the tweet Like for this tweet id {}", tweetId);

	}

	public String update(String tweetId, Tweet tweet) {
		dbMapper.save(tweet, new DynamoDBSaveExpression().withExpectedEntry("tweetId",
				new ExpectedAttributeValue(new AttributeValue().withS(tweetId))));
		return tweetId;
	}

	public void replyTweet(List<Tweet> replyTweet, String id) {
		logger.info("inside update {}", replyTweet);
		Tweet tweetById = getTweetById(id);
		logger.info("got the tweet {}", tweetById);
		tweetById.setReply(replyTweet);
		update(id, tweetById);

	}

	public Tweet save(Tweet tweet) {
		dbMapper.save(tweet);
		return tweet;
	}

	public Tweet getTweetById(String tweetId) {
		return dbMapper.load(Tweet.class, tweetId);
	}

	public Tweet getTweetByuserID(String userId) {
		return dbMapper.load(Tweet.class, userId);
	}

	public String delete(String tweetId) {
		Tweet load = dbMapper.load(Tweet.class, tweetId);
		dbMapper.delete(load);
		return "deleted";
	}

	public List<Tweet> getallTweets() {
		PaginatedScanList<Tweet> scan = dbMapper.scan(Tweet.class, scanExpression);
		return scan;
	}
}
