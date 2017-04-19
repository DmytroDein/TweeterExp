package twitter.domain.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import twitter.domain.Tweet;

import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
//@ContextConfiguration(locations = {"classpath:/spring.xml", "classpath:/service.xml"})
@ContextHierarchy({
        @ContextConfiguration("classpath:/spring.xml"),
        @ContextConfiguration("classpath:/service.xml")
})
public class TweetServiceImplTest {

    @Autowired
    private TweetService tweetService;

    @Test
    @Repeat(10)
    public void getTweetFoundTest() throws Exception {
        System.out.println("Testing tweet found");
        Tweet tweet = tweetService.getTweet(1);
        Assert.assertEquals(tweet.getTweetId(), 1);

    }

    @Test
    public void getTweetNotFoundTest() throws Exception {
        System.out.println("Testing tweet not found");
        Tweet tweet = tweetService.getTweet(999);
        Assert.assertNull(tweet);

    }

    @Test
    public void getUser() throws Exception {
        System.out.println("test ");
    }

}