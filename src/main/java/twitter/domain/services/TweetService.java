package twitter.domain.services;


import twitter.domain.Tweet;
import twitter.domain.User;

import java.util.List;

public interface TweetService {

    List<Tweet> findAll();
    void addTweet(Tweet tweet);
    Tweet createTweet(User user, String tweetText);
    Tweet createEmptyTweet();

}
