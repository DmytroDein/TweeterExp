package twitter.infrastructure.custom;

import twitter.domain.repository.TweetRepositoryImpl;
import twitter.domain.services.TweetServiceImpl;

import java.util.HashMap;
import java.util.Map;


public class JavaConfig implements Config {

    private final Map<String, Class<?>> classes = new HashMap<>();

    {
        classes.put("tweetRepository", TweetRepositoryImpl.class);
        classes.put("tweetService", TweetServiceImpl.class);
//        classes.put("user", User.class);
    }

    @Override
    public Class<?> getImpl(String name) {
        return classes.get(name);
    }

    /*@Override
    public String[] getImplParameters(String name) {
        return beansConstructorParameters.get(name);
    }*/
}
