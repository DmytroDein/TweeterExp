package twitter.domain;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component()
@Scope("prototype")
@Lazy
public class Tweet {

    private User user;
    private String text;
    private LocalDateTime date;
    private List<User> retweetedBy;
    private List<User> likedBy;
    private List<User> mentionedInTweet;

    public Tweet() {
    }

    public Tweet(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user=" + user +
                ", text='" + text + '\'' +
                '}';
    }
}
