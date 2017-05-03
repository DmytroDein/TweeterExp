package twitter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import twitter.domain.Tweet;
import twitter.domain.services.TweetService;
import twitter.domain.services.TweetServiceImpl;
import twitter.rest.exceptions.ExceptionDescriptor;
import twitter.rest.exceptions.NoSuchTweetException;

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
                        if (tweet == null){
                            throw new NoSuchTweetException("Tweet {" + tweetId + "} not found!");
                        }
                        setValue(tweet);
                    }
                });
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchTweetException.class})
//    public String[] onTweetException(Exception ex){
    public ExceptionDescriptor onTweetException(Exception ex){
//        return new String[]{"Error!"};
        return new ExceptionDescriptor("Tweet", ex.getMessage());
    }

}
