package twitter.domain;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Scope("prototype")
@Lazy
public class TimeLine implements TimeLineActions {

    public static final int DEFAULT_TIMELINE_TWEETS_LIMIT = 21;
    private List<Tweet> tweets = new ArrayList<>();
    private final User user;
    private LocalDateTime dateOfCreation;

    public TimeLine(User user) {
        this.user = user;
    }

    @Override
    public void put(Tweet tweet){
        tweets.add(tweet);
    }

    @Override
    public Iterable<Tweet> getTweets(){
        return new ArrayList<>(tweets);
    }

    @Override
    public void refreshTimeLine(){
        refreshTimeLine(DEFAULT_TIMELINE_TWEETS_LIMIT);
    }

    @Override
    public void refreshTimeLine(int tweetsLimit){
        Stream<Tweet> usersTweetsStream = user.getUsersTweets().stream();
        Stream<Tweet> usersSubscriptionsTweetsStream = user
                .getSubscribedTo()
                .stream()
                .flatMap(e -> e.getUsersTweets().stream());
        tweets = Stream.concat(usersTweetsStream, usersSubscriptionsTweetsStream)
                .sorted((o1, o2) -> (int)o1.getDate().until(o2.getDate(), ChronoUnit.NANOS))
                .distinct()
                .limit(tweetsLimit)
                .collect(Collectors.toList());
        dateOfCreation = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "tweets=" + tweets +
                ", user=" + user +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
