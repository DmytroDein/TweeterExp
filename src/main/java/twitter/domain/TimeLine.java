package twitter.domain;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("protorype")
@Lazy
public class TimeLine {

    private final List<Tweet> tweets = new ArrayList<>();
    private final User user;

    public TimeLine(User user) {
        this.user = user;
    }

    public void put(Tweet tweet){
        tweets.add(tweet);
    }

    public Iterable<Tweet> tweets(){
        return new ArrayList<>(tweets);
    }

}
