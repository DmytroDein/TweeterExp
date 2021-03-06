package twitter.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.Controller;
import org.springframework.stereotype.Controller;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.domain.services.TweetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class TweeterController {
//public class SpringHelloController implements Controller {

    private TweetService tweetService;

    @Autowired
    public TweeterController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

  /*  @Autowired
    public void setTweetService(TweetService tweetService) {
        this.tweetService = tweetService;
    }*/

    //    @Override
//    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    @RequestMapping("/hello")
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try (PrintWriter out = response.getWriter()){
            out.write("<p> Hello from Tweeter Controller!</p>");
            tweetService.findAll().forEach(e ->
                    out.write("Tweet: " + e +  "<br/>")
            );
        }
        //return null;
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
//    @GetMapping("/getTweets")
    public String allTweets(Model model){
        List<Tweet> allTweets = tweetService.findAll();
        model.addAttribute("tweets", allTweets);
        return "jsp-tweets-page";
    }

//    @RequestMapping(value = "/tweet", method = RequestMethod.GET)  //http://localhost:8888/web/tweet?id=1
    @RequestMapping(value = "/tweet/{id}", method = RequestMethod.GET) //http://localhost:8888/web/tweet/1
    @ResponseBody
//    public String allTweets(@RequestParam("id") long id){  //http://localhost:8888/web/tweet?id=1
    public String allTweets(@PathVariable("id") long id){  //http://localhost:8888/web/tweet/1
        Tweet tweet = tweetService.getTweet(id);
        return tweet.toString();
    }

    @RequestMapping(value = "/tweet/modify", method = RequestMethod.GET)   //http://localhost:8888/web/tweet/modify?id=1
    @ResponseBody
    public String allTweets(@RequestParam("id") Tweet tweet){
        if(Objects.nonNull(tweet)) {
            return tweet.toString();
        } else return "Tweet not found!";
    }

    /*//@InitBinder // instead of TweetConverter working
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
    }*/


    @RequestMapping(value = "/tweet/error", method = RequestMethod.GET)
    @ResponseBody
    public String getError(){
        throw new UnsupportedOperationException("Custom exception throwed and catched.");
    }

}
