package twitter.domain.repository;


import twitter.domain.Tweet;

import java.util.List;

public interface TweetRepository {

    void save(Tweet tweet);
    List<Tweet> findAll();

}
