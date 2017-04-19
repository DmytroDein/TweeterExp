package twitter.domain;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component()
@Scope("prototype")
@Lazy
public class Tweet implements TweetActions{

    private User user;
    private String text;

    private LocalDateTime date = LocalDateTime.now();
    private List<User> retweetedBy;
    private Set<User> likedBy = new LinkedHashSet<>();
    private Set<User> mentionedInTweet = new LinkedHashSet<>();

    public Tweet() {
    }

    public Tweet(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<User> getRetweetedBy() {
        return retweetedBy;
    }

    public void setRetweetedBy(List<User> retweetedBy) {
        this.retweetedBy = retweetedBy;
    }

    public Set<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Set<User> likedBy) {
        this.likedBy = likedBy;
    }

    public Set<User> getMentionedInTweet() {
        return mentionedInTweet;
    }

    public void setMentionedInTweet(Set<User> mentionedInTweet) {
        this.mentionedInTweet = mentionedInTweet;
    }

    @Override
    public void like(User user) {
        if(!this.user.equals(user)) {
            likedBy.add(user);
        }
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
    public void mentionUser(User user) {
        user.mentionUser(this);
        mentionedInTweet.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (!user.equals(tweet.user)) return false;
        if (!text.equals(tweet.text)) return false;
        return date.equals(tweet.date);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user=" + user +
                ", text='" + text + '\'' +
                '}';
    }
}
