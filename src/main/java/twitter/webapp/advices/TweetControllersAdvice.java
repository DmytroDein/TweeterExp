package twitter.webapp.advices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import twitter.dispatcher.TweetController;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.domain.services.TweetService;
import twitter.webapp.TweeterController;

import java.beans.PropertyEditorSupport;

@ControllerAdvice(assignableTypes = {TweeterController.class})
//@ControllerAdvice
public class TweetControllersAdvice {

    private TweetService tweetService;

    @Autowired
    public TweetControllersAdvice(TweetService tweetService) {
        this.tweetService = tweetService;
    }

/*    //    @ModelAttribute(value = "unnamedUser")
    @ModelAttribute
    public User unNamedUser(){
        System.out.println("UnNamedUser from 'AllControllersAdvice'");
        return new User("unNamed-User");
    }*/

    @InitBinder // instead of TweetConverter working
    public void tweetBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(
                Tweet.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
//                        super.setAsText(text);
                        long tweetId = Long.parseLong(text);
                        Tweet tweet = tweetService.getTweet(tweetId);
                        setValue(tweet);
                    }
                });
    }

}
