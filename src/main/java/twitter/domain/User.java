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

    private long userId = userIdCounter++;
    private String userName;
    private List<Tweet> usersTweets = new ArrayList<>();
    private List<User> subscribedTo;
    private Set<Tweet> mentionedInTweets = new LinkedHashSet<>();
    private UserProfile profile;

    public User(String userName) {
        this.userName = userName;
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
        return subscribedTo;
    }

    public void setSubscribedTo(List<User> subscribedTo) {
        this.subscribedTo = subscribedTo;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void mentionUser(Tweet tweet){
        mentionedInTweets.add(tweet);
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

        return userName.equals(user.userName);

    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
