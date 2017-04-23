package twitter.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import twitter.domain.User;
import twitter.domain.services.TweetService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController  {

    private TweetService tweetService;

    @Autowired
    public UserController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showUserForm(){
        return "create-user";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody String createUser(@RequestParam("user") String user){
//    public @ResponseBody String createUser(User user){
//    public @ResponseBody String createUser(@ModelAttribute User user){
//    public @ResponseBody String createUser(@RequestAttribute(name="userAttributeName")  User user){   ////???? unchecked
//        System.out.println(user);
        User createdUser = new User(user);
        return createdUser.toString();
    }

//    @ModelAttribute(value = "unnamedUser")
    /*@ModelAttribute
    public User unNamedUser(){
        System.out.println("UnNamedUser");
        return new User("unNamed-User");
    }*/

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String usersList(Model model){
        List<User> usersList = tweetService.getUsers();
        //System.out.println("Users list size: " + usersList.size());
        //usersList.stream().forEach(System.out::println);
        model.addAttribute("users", usersList);
        return "users-list";
    }

/*    @RequestMapping(value = "/addedit", method = RequestMethod.GET)
    public String addNewUser(Model model){
        return "create-update-user";
    }*/


    @RequestMapping(value = "/addedit", method = RequestMethod.GET)
//    @RequestMapping(value = "/addedit")
    public String createEditUser(@ModelAttribute User user){
        System.out.println(user);
        //tweetService.saveUser(user);
//        return "redirect:all";
        return "create-update-user";
    }

/*    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute User user) {
        System.out.println("updateUser() => user: " + user);
        //tweetService.saveUser(user);
        return "redirect:all";
    }*/


    /*@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable("id") long userId, Model model){
        User user = tweetService.getUser(userId);
        System.out.println("editUser() found: " + user);
        model.addAttribute("cur-user", user);
        model.addAttribute("name", user.getUserName());
        model.addAttribute("id", user.getUserId());
        return "edit-user-form";
    }*/

}
