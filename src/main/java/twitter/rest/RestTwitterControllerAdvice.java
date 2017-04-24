package twitter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import twitter.domain.Tweet;
import twitter.domain.services.TweetService;
import twitter.domain.services.TweetServiceImpl;

import java.beans.PropertyEditorSupport;

@RestControllerAdvice(assignableTypes = {RestTwitterController.class})
public class RestTwitterControllerAdvice {

    private TweetService tweetService;

    @Autowired
    public RestTwitterControllerAdvice(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @InitBinder
    public void tweetBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(
                Tweet.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
                        long tweetId = Long.parseLong(text);
                        Tweet tweet = tweetService.getTweet(tweetId);
                        setValue(tweet);
                    }
                });
    }

}
