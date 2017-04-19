package twitter.webapp.advices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import twitter.domain.User;
import twitter.domain.services.TweetService;
import twitter.webapp.UserController;

@ControllerAdvice(assignableTypes = {UserController.class})
//@ControllerAdvice
public class UserControllerAdvice {

    private TweetService tweetService;

    @Autowired
    public UserControllerAdvice(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    //    @ModelAttribute(value = "unnamedUser")
    @ModelAttribute
    public User unNamedUser(){
        System.out.println("UnNamedUser from 'UserControllerAdvice'");
        return new User("unNamed-User");
    }
}
