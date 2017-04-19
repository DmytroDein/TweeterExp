package twitter.webapp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import twitter.domain.Tweet;
import twitter.domain.services.TweetService;

public class TweetConverter implements Converter<String, Tweet> {

    @Autowired
    TweetService tweetService;

    @Override
    public Tweet convert(String textValue) {
        long tweetId = Long.parseLong(textValue);
        Tweet tweet = tweetService.getTweet(tweetId);
        return tweet;
    }
}
