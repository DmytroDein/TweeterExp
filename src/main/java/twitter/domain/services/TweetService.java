package twitter.domain.services;


import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.domain.repository.TweetRepository;
import twitter.domain.repository.UserRepository;

import java.util.List;

public interface TweetService {

    List<Tweet> findAll();
    void addTweet(Tweet tweet);
    Tweet createTweet(User user, String tweetText);
    Tweet createEmptyTweet();

    TweetRepository getTweetRepository();
    UserRepository getUserRepository();

    Tweet getTweet(long tweetId);

    User getUser(String userName);
    User getUser(long userId);

    List<User> getUsers();
    void createUser(String userName);

    void saveUser(User user);
}
