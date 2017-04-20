package twitter.domain;


public interface TimeLineActions {
    void put(Tweet tweet);
    Iterable<Tweet> getTweets();
    void refreshTimeLine();
    void refreshTimeLine(int tweetsLimit);
}
