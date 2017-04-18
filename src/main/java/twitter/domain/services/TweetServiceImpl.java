package twitter.domain.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.infrastructure.annotations.Benchmark;
import twitter.domain.repository.TweetRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("tweetService")
//@Scope("prototype")
public class TweetServiceImpl implements TweetService, ApplicationContextAware{

    //@Autowired
    private final TweetRepository tweetRepository;
    ApplicationContext serviceContext;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
//    @Benchmark
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    @Override
    //@Benchmark(value = true)
    public void addTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.serviceContext = applicationContext;
    }

    @Override
    //@Benchmark(value = true)
    public Tweet createTweet(User user, String tweetText){
        //System.out.println("Creating tweet...");
        Tweet tweet = createNewTweet();
        tweet.setUser(user);
        tweet.setText(tweetText);
        return tweet;
    }

    private Tweet createNewTweet() {
        return (Tweet) serviceContext.getBean("tweet");
    }

    //@Lookup
    public Tweet createEmptyTweet(){
        return null;
    }

    @PostConstruct
    public void mockTweetsData(){
        User user1 = (User) serviceContext.getBean("user", "Douglas");
        Tweet tweetFromUser1 = createTweet(user1, "Some text #1 from user1!" );
        addTweet(tweetFromUser1);
        tweetFromUser1 = createTweet(user1, "Some text #2 from user1!" );
        addTweet(tweetFromUser1);
        tweetFromUser1 = createTweet(user1, "Some text #3 from user1!" );
        addTweet(tweetFromUser1);

        User user2 = (User) serviceContext.getBean("user", "Michael");
        Tweet tweetFromUser2 = createTweet(user2, "Some text #1 from user2!" );
        addTweet(tweetFromUser2);
        tweetFromUser2 = createTweet(user2, "Some text #2 from user2!" );
        addTweet(tweetFromUser2);
        tweetFromUser2 = createTweet(user2, "Some text #3 from user2!" );
        addTweet(tweetFromUser2);
    }

}
