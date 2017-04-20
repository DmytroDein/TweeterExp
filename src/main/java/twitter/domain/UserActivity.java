package twitter.domain;


public interface UserActivity {
    void mentionUser(Tweet tweet);
    void subscribeTo(User user);
    void reTweet(Tweet tweet);
}
