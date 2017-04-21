package twitter;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import twitter.domain.TimeLineActions;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.domain.services.TweetService;

import java.util.Arrays;

public class SpringAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml"}, true);
        ConfigurableApplicationContext childContext = new ClassPathXmlApplicationContext(new String[]{"service.xml"}, context);

        TweetService tweetService = (TweetService)childContext.getBean("tweetService");

/*//        User user1 = (User) context.getBean("user", "Douglas");
        User user1 = tweetService.getUser("Douglas");
        Tweet tweetFromUser1 = tweetService.createTweet(user1, "Some text #1 from user1!" );
        tweetService.addTweet(tweetFromUser1);
        tweetFromUser1 = tweetService.createTweet(user1, "Some text #2 from user1!" );
        tweetService.addTweet(tweetFromUser1);
        tweetFromUser1 = tweetService.createTweet(user1, "Some text #3 from user1!" );
        tweetService.addTweet(tweetFromUser1);

//        User user2 = (User) childContext.getBean("user", "Michael");
        User user2 = tweetService.getUser("Michael");
        Tweet tweetFromUser2 = tweetService.createTweet(user2, "Some text #1 from user2!" );
        tweetService.addTweet(tweetFromUser2);
        tweetFromUser2 = tweetService.createTweet(user2, "Some text #2 from user2!" );
        tweetService.addTweet(tweetFromUser2);
        tweetFromUser2 = tweetService.createTweet(user2, "Some text #3 from user2!" );
        tweetService.addTweet(tweetFromUser2);*/

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

    /*    Tweet tweet = tweetService.createEmptyTweet();
        System.out.println("\nEmpty tweet by 'lookup': " + tweet.getClass().getName());*/

        //System.out.println(tweetService.getClass().getName());

        // Sample operations for functionality enhancing
//        User user3 = (User) context.getBean("user", "Freddy");
        User user3 = tweetService.getUser("Freddy");
        Tweet tweetFromUser3 = tweetService.createTweet(user3, "Some text #1 from user3!" );
        tweetService.addTweet(tweetFromUser3);

        // Like tweet
//        User user1 = (User) context.getBean("user", "Douglas");
        System.out.println("\n---------------------------- Like check: ----------------------------------");
        User user1 = tweetService.getUser("Douglas");
        tweetFromUser3.like(user1);
        System.out.println("Tweet: '" + tweetFromUser3.getText() + "'" + "; Author: " + tweetFromUser3.getUser());
        System.out.print("Liked by: ");
        tweetFromUser3.getLikedBy().forEach(System.out::println);

        // Mention
        System.out.println("\n---------------------------- Mention check: --------------------------------");
        tweetFromUser3.mentionUser(user1);
        System.out.println("Tweet: '" + tweetFromUser3.getText() + "'" + "; Author: " + tweetFromUser3.getUser());
        System.out.print("Tweet mentions: ");
        tweetFromUser3.getMentionedInTweet().forEach(System.out::println);

        // ReTweeting
//        tweetFromUser3.reTweet(user1);
        System.out.println("\n------------------------------------------------- ReTweeting check: ---------------------------------------------------");
        user1.reTweet(tweetFromUser3);
        System.out.print("User's '" + user1.getUserName() + "' tweets:\n");
        user1.getUsersTweets().forEach(System.out::println);


        // TimeLine formation
        System.out.println("\n------------------------------------ TimeLine formation check (backward sorting): --------------------------------------");
//        System.out.println("TimeLine of '" + user1.getUserName() + "':");
        System.out.println("TimeLine of '" + user1 + "':");
        TimeLineActions timeLine = (TimeLineActions)childContext.getBean("timeLine", user1);
        User user2 = tweetService.getUser("Michael");
        user1.subscribeTo(user2);
        user1.subscribeTo(user3);
        timeLine.refreshTimeLine();
        timeLine.getTweets().forEach(System.out::println);

        // Close contexts
        waitInMillis(1000);
        childContext.close();
        context.close();

    }

    private static void waitInMillis(int waitMillis) {
        try {
            Thread.sleep(waitMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
