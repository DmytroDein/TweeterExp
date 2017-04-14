package twitter.domain;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@Lazy
public class User {
    private String userName;
    private List<Tweet> usersTweets;
    private List<User> subscribedTo;
    private List<Tweet> mentionedInTweets;
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

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
