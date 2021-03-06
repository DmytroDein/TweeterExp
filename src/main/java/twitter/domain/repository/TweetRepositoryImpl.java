package twitter.domain.repository;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import twitter.domain.Tweet;
import twitter.infrastructure.annotations.Benchmark;
import twitter.infrastructure.annotations.PostConstructAnnotation;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository("TweeterRepository")
@Lazy
public class TweetRepositoryImpl implements TweetRepository {

    private List<Tweet> tweetRepo = new ArrayList<>();

    @Override
//    @Benchmark
    public void save(Tweet tweet) {
        tweetRepo.add(tweet);
    }

    @Override
    public Tweet findById(long tweetId) {
        return tweetRepo.stream()
                .filter(e -> e.getTweetId() == tweetId)
                .findFirst()
                .orElse(null);
    }

    @Override
//    @Benchmark(value = true)
    public List<Tweet> findAll() {
        return new ArrayList<>(tweetRepo);
    }

    @PostConstruct
    public void init(){
        System.out.println("TweetRepositoryImpl: init() called by Spring.");
    }

    //@PostConstructAnnotation
    public void postConstructMethod(){
        System.out.println("postConstructMethod() called over annotation '@PostConstructAnnotation'");
    }

}
