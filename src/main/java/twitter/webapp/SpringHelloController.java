package twitter.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.domain.services.TweetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpringHelloController {
//public class SpringHelloController implements Controller {

    private TweetService tweetService;

    @Autowired
    public SpringHelloController(TweetService tweetService) {
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
            out.write("<p> hello from Hello controller!</p>");
            tweetService.findAll().forEach(e ->
                    out.write("Tweet: " + e +  "<br/>")
            );
        }
        //return null;
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
//    @GetMapping("/tweets")
    public String allTweets(Model model){
        /*List<Tweet> allTweets = tweetService.findAll();
        model.addAttribute("tweets", allTweets);*/

        List<Integer> allTweets = new ArrayList<>();
        allTweets.add(1);
        allTweets.add(2);
        allTweets.add(3);
        allTweets.add(4);

        model.addAttribute("tweets", allTweets);
        return "jsp-tweets-page";
    }
}
