package twitter.webapp.advices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import twitter.domain.User;
import twitter.domain.services.TweetService;
import twitter.webapp.UserUpdateController;

import java.beans.PropertyEditorSupport;

@ControllerAdvice(assignableTypes = {UserUpdateController.class})
public class UserUpdateControllerAdvice {
    private TweetService tweetService;

    @Autowired
    public UserUpdateControllerAdvice(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @ModelAttribute
    public User getUserFromForm(@RequestParam(name = "userName", required = false) User user){
        System.out.println("getUserFromForm() => user: " + user);
        return user;
    }
}
