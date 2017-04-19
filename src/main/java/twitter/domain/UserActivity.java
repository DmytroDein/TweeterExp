package twitter.domain;


public interface UserActivity {
    void mentionUser(Tweet tweet);
    void reTweet(Tweet tweet);
}
