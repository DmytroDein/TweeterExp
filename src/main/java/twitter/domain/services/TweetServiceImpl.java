package twitter.domain.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.domain.repository.UserRepository;
import twitter.infrastructure.annotations.Benchmark;
import twitter.domain.repository.TweetRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service("tweetService")
@Lazy
@DependsOn(value = {"TweeterRepository", "UserRepository"})
public class TweetServiceImpl implements TweetService, ApplicationContextAware, InitializingBean{
//public class TweetServiceImpl implements TweetService, ApplicationContextAware{

    private TweetRepository tweetRepository;
    private UserRepository userRepository;
    ApplicationContext serviceContext;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository) {
        System.out.println("TweetService(repo, repo) starting...");
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        //System.out.println(userRepository);
        //System.out.println(tweetRepository);
    }

    @Override
    public TweetRepository getTweetRepository() {
        return tweetRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
//    @Benchmark
    public Tweet getTweet(long tweetId) {
        return tweetRepository.findById(tweetId);
    }

    @Override
//    @Benchmark
    public User getUser(long userId) {
        return userRepository.getUser(userId);
    }

    @Override
//    @Benchmark
    public List<User> getUsers() {
        return userRepository.findAllUsers();
    }

    @Override
//    @Benchmark
    public User getUser(String userName) {
        Optional<User> checkedUser = Optional.ofNullable(userRepository.getUser(userName));
        return checkedUser.orElseGet(() -> {
            User user = (User)serviceContext.getBean("user", userName);
            userRepository.save(user);
            return user;
        });
    }

    @Override
//    @Benchmark(value = true)
    public void addTweet(Tweet tweet) {
        tweet.getUser().getUsersTweets().add(tweet);
        tweetRepository.save(tweet);
    }

    @Override
//    @Benchmark
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    @Override
//    @Benchmark(value = true)
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

    @Lookup
    public Tweet createEmptyTweet(){
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.serviceContext = applicationContext;
    }

    @Override
//    @Benchmark
    public void createUser(String userName) {
        User user = (User) serviceContext.getBean("user", userName);
        userRepository.save(user);
    }

    @Override
//    @Benchmark
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mockTweetsData();
    }

    //    @PostConstruct  // problem of working with double proxy - changed to implementing 'InitializingBean' interface
    public void mockTweetsData(){
//        User user1 = (User) serviceContext.getBean("user", "Douglas");
        User user1 = getUser("Douglas");
        Tweet tweetFromUser1 = createTweet(user1, "Some text #1 from user1!" );
        addTweet(tweetFromUser1);
        tweetFromUser1 = createTweet(user1, "Some text #2 from user1!" );
        addTweet(tweetFromUser1);
        tweetFromUser1 = createTweet(user1, "Some text #3 from user1!" );
        addTweet(tweetFromUser1);

//        User user2 = (User) serviceContext.getBean("user", "Michael");
        User user2 = getUser("Michael");
        Tweet tweetFromUser2 = createTweet(user2, "Some text #1 from user2!" );
        addTweet(tweetFromUser2);
        tweetFromUser2 = createTweet(user2, "Some text #2 from user2!" );
        addTweet(tweetFromUser2);
        tweetFromUser2 = createTweet(user2, "Some text #3 from user2!" );
        addTweet(tweetFromUser2);
    }
}
