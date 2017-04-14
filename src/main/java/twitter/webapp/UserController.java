package twitter.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twitter.domain.User;

@Controller
@RequestMapping("/user")
public class UserController  {

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showUserForm(){
        return "create-user";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody String createUser(@RequestParam("user") String user){
//    public @ResponseBody String createUser(User user){
//    public @ResponseBody String createUser(@ModelAttribute User user){
        System.out.println(user);
        User createdUser = new User(user);
        return createdUser.toString();
    }

//    @ModelAttribute(value = "unnamedUser")
    /*@ModelAttribute
    public User unNamedUser(){
        System.out.println("UnNamedUser");
        return new User("unNamed-User");
    }*/
}
