package twitter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("prototype")
@Lazy
public class User implements UserActivity{
    private static long userIdCounter = 1;

    private long userId;
    private String userName;
    private List<Tweet> usersTweets = new ArrayList<>();
    private Set<User> subscribedTo = new HashSet<>();
    private Set<Tweet> mentionedInTweets = new LinkedHashSet<>();
    private UserProfile profile;

    public User() {
        userId = userIdCounter++;
    }

    public User(long userId) {
        this.userId = userId;
    }

    public User(String userName) {
        this.userName = userName;
        userId = userIdCounter++;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Tweet> getUsersTweets() {
        return usersTweets;
    }

  /*  @Autowired
    @Scope("prototype")*/
    public void setUsersTweets(List<Tweet> usersTweets) {
        this.usersTweets = usersTweets;
    }

    public List<User> getSubscribedTo() {
//        return subscribedTo;
        return new ArrayList<>(subscribedTo);
    }

    public void setSubscribedTo(List<User> subscribedTo) {
        this.subscribedTo = new HashSet<>(subscribedTo);
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public Set<Tweet> getMentionedInTweets() {
        return mentionedInTweets;
    }

    public void setMentionedInTweets(Set<Tweet> mentionedInTweets) {
        this.mentionedInTweets = mentionedInTweets;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public void mentionUser(Tweet tweet){
        mentionedInTweets.add(tweet);
    }

    @Override
    public void subscribeTo(User user){
        subscribedTo.add(user);
    }

    @Override
    public void reTweet(Tweet tweet) {
        usersTweets.add(tweet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        return userName != null ? userName.equals(user.userName) : user.userName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
