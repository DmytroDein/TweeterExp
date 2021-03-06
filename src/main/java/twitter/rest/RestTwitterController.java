package twitter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import twitter.domain.Tweet;
import twitter.domain.services.TweetService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

@RestController
//@RequestMapping("/rest")
public class RestTwitterController {


    private TweetService tweetService;

    @Autowired
    public RestTwitterController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "application/json")
    public String[] helloRest(){
//    public String helloRest(){
//        return "Hello from REST!";
        return new String[]{"Hello from REST!"};
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tweets", produces = "application/json")
    public Iterable<Tweet> listTweets(){
        List<Tweet> container = tweetService.findAll();
        for(Tweet t: container){
            //killTweetData(t);
        }
        return container;
    }

    /*@RequestMapping(method = RequestMethod.GET, value = "/tweets/{tweetId}", produces = "application/json")
    public Tweet getTweet(@PathVariable("tweetId") long tweetId){
        return tweetService.getTweet(tweetId);
    }*/

    @RequestMapping(method = RequestMethod.GET, value = "/tweets/{tweetId}", produces = "application/json")
    public Tweet getTweet(@PathVariable("tweetId") Tweet tweet){
        //killTweetData(tweet);
        return tweet;
    }

    // Works without annotations '@JsonManagedReference' and '@JsonBackReference' with nulls
    @RequestMapping(method = RequestMethod.POST, value = "/tweets",
            produces = "application/json", consumes = "application/json")
    public Tweet newTweet(@RequestBody Tweet tweet){
        //killTweetData(tweet);
        return tweet;
    }

    // for or without '@JsonManagedReference' and '@JsonBackReference'
    /*private void killTweetData(Tweet t) {
        t.setMentionedInTweet(null);
        t.setLikedBy(null);
        t.setRetweetedBy(null);
        t.setUser(null);
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/tweet",
            produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> createTweet(@RequestBody Tweet tweet, UriComponentsBuilder builder) {
        tweetService.addTweet(tweet);
        System.out.println("Acquired tweet: " + tweet);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/tweet/{tweetId}").buildAndExpand(4).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = {"/tweetht/{tweetId}"},
            produces = "application/json")
    public Tweet tweetHATEOAS(@PathVariable("tweetId") Long id) {
        return tweetService.getTweet(id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = {"/tweetht"},
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<Tweet> newTweetHATEOAS(@RequestBody Tweet tweet) {
        tweetService.addTweet(tweet);
        System.out.println(tweet);

        HttpHeaders headers = new HttpHeaders();
        Link link = linkTo(methodOn(RestTwitterController.class).tweetHATEOAS(4L)).withSelfRel();
        Link linkAll = linkTo(methodOn(RestTwitterController.class).listTweets()).withRel("all");
        tweet.add(link);
        tweet.add(linkAll);

        headers.setLocation(URI.create(link.getHref()));

        ResponseEntity<Tweet> responseEntity = new ResponseEntity<>(tweet, HttpStatus.CREATED);
        return responseEntity;

    }

}
