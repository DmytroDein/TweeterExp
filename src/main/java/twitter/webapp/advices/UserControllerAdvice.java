package twitter.webapp.advices;


import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.domain.services.TweetService;
import twitter.webapp.UserController;

import java.beans.PropertyEditorSupport;

@ControllerAdvice(assignableTypes = {UserController.class})
//@ControllerAdvice
public class UserControllerAdvice {

    private TweetService tweetService;

    @Autowired
    public UserControllerAdvice(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    //    @ModelAttribute(value = "unnamedUser")
    /*@ModelAttribute
    public User unNamedUser(){
        //System.out.println("UnNamedUser from 'UserControllerAdvice'");
        System.out.println("@ModelAttribute acquired user: " + user);
        return user;@RequestParam(name = "userName", required = false) User user
    }*/

    @ModelAttribute
    public User preparedUser(@RequestParam(name = "userId", required = false) User user){
       /* long localUserId = user.getUserId();
        if(localUserId != 0) {
            user = tweetService.getUser(localUserId);
        }*/
        return user;
    }

    @InitBinder
    public void userBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(
                User.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
                        User user;
                        if (text == null){
                            user = new User();
                        } else {
                            long userId = Long.parseLong(text);
                            user = tweetService.getUser(userId);
                        }
                        setValue(user);
                    }
                });
    }
}
