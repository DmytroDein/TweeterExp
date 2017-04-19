package twitter.domain;


public interface TweetActions {
    void like(User user);
    void mentionUser(User user);
}
