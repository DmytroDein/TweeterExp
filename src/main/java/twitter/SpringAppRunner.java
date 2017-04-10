package twitter;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.domain.repository.TweetRepository;
import twitter.domain.services.TweetService;

import java.util.Arrays;

public class SpringAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml"}, true);
        ConfigurableApplicationContext childContext = new ClassPathXmlApplicationContext(new String[]{"service.xml"}, context);

        TweetService tweetService = (TweetService)childContext.getBean("tweetService");
        User user1 = (User) context.getBean("user", "Douglas");
        Tweet tweetFromUser1 = tweetService.createTweet(user1, "Some text #1 from user1!" );
        tweetService.addTweet(tweetFromUser1);
        tweetFromUser1 = tweetService.createTweet(user1, "Some text #2 from user1!" );
        tweetService.addTweet(tweetFromUser1);
        tweetFromUser1 = tweetService.createTweet(user1, "Some text #3 from user1!" );
        tweetService.addTweet(tweetFromUser1);

        User user2 = (User) childContext.getBean("user", "Michael");
        Tweet tweetFromUser2 = tweetService.createTweet(user2, "Some text #1 from user2!" );
        tweetService.addTweet(tweetFromUser2);
        tweetFromUser2 = tweetService.createTweet(user2, "Some text #2 from user2!" );
        tweetService.addTweet(tweetFromUser2);
        tweetFromUser2 = tweetService.createTweet(user2, "Some text #3 from user2!" );
        tweetService.addTweet(tweetFromUser2);

        System.out.println("\n---------------- List of tweets ------------------");
        tweetService.findAll().forEach(System.out::println);

        System.out.println("\n------------- context -----------");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        System.out.println("\n------------- childContext -----------");
        System.out.println(Arrays.toString(childContext.getBeanDefinitionNames()));

        /*BeanDefinition beanDefinition = context.getBeanFactory().getBeanDefinition("TweeterRepository");
        System.out.println("----------- context ------------");
        System.out.println(beanDefinition);

        beanDefinition.setScope("prototype");
        //ctx.refresh();
        beanDefinition = context.getBeanFactory().getBeanDefinition("TweeterRepository");
        System.out.println(beanDefinition);*/

        /*Temp temp = (Temp)ctx2.getBean("temp");

        System.out.println("-------------CTX2-----------");
        System.out.println(Arrays.toString(ctx2.getBeanDefinitionNames()));

        System.out.println(ctx2.getBeanFactory().getBeanDefinition("tempable"));*/

        /*Tweet tweet = tweetService.createEmptyTweet();
        System.out.println("\nEmpty tweet by 'lookup': " + tweet.getClass().getName());*/

        context.close();

    }
}
