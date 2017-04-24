package twitter.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import twitter.domain.User;
import twitter.domain.services.TweetService;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserUpdateController {
    private TweetService tweetService;

    @Autowired
    public UserUpdateController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    /*@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute User user) {
        System.out.println("updateUser() => user: " + user);
        //tweetService.saveUser(user);
//        return "redirect:all";
        return "create-update-user";
    }*/

    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updateUser(@RequestParam(value = "userName") String userName) {
    public String updateUser(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()){
            System.out.println("Errors:" + result.getErrorCount());
//            System.out.println(Arrays.toString(result.getSuppressedFields()));
            System.out.println(result);
        }
        System.out.println("updateUser()=> updated user: " + user);
        tweetService.saveUser(user);
        return "redirect:all";
    }
}
