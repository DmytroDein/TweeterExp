package twitter;


import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.infrastructure.custom.ApplicationContext;
import twitter.infrastructure.custom.Config;
import twitter.infrastructure.custom.Context;
import twitter.infrastructure.custom.JavaConfig;
import twitter.domain.services.TweetService;

public class AppRunner {
    public static void main(String[] args) throws Exception {
       /* TimeLine timeLine = new TimeLine();
        User user = new User("Douglas");
        timeLine.put(new Tweet(user, "Some text!"));*/

        /*TweetRepository tweetRepository = new TweetRepositoryImpl();
        User user = new User("Douglas");
        tweetRepository.save(new Tweet(user, "Some text №1!"));
        tweetRepository.save(new Tweet(user, "Some text №2!"));

        tweetRepository.findAll().forEach(System.out::println);*/
//---------------------------------------------------------------------------

        /*Config config = new JavaConfig();
        InitialContext initialContext = new InitialContext(config);
        TweetRepository repository = (TweetRepository) initialContext.lookup("TweetRepo");
        User user = new User("Douglas");
        repository.save(new Tweet(user, "Some text №1!"));
        repository.save(new Tweet(user, "Some text №2!"));
        repository.findAll().forEach(System.out::println);*/
//---------------------------------------------------------------------------
       /* Config config = new JavaConfig();
        Context context = new ApplicationContext(config);
        TweetRepository repository = context.getBeanByClass("TweetRepo");
        User user = new User("Douglas");
        repository.save(new Tweet(user, "Some text №1!"));
        repository.save(new Tweet(user, "Some text №2!"));
        repository.save(new Tweet(user, "Some text №3!"));
        repository.findAll().forEach(System.out::println);

        System.out.println("Done!");*/
//--------------- Services work ------------------------------------------------------------
        Config config = new JavaConfig();
        Context context = new ApplicationContext(config);
        //TweetRepository repository = context.getBeanByClass("TweetRepositoryImpl");
        TweetService tweetService = context.getBean("tweetService");

        User user = new User("Douglas");
        tweetService.addTweet(new Tweet(user, "Some text №1!"));
        tweetService.addTweet(new Tweet(user, "Some text №2!"));
        tweetService.addTweet(new Tweet(user, "Some text №3!"));

        tweetService.findAll().forEach(System.out::println);
        System.out.println("Done!");
    }
}
