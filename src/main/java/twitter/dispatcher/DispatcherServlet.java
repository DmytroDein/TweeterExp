package twitter.dispatcher;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import twitter.dispatcher.mapping.HandlerMapping;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.domain.services.TweetService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DispatcherServlet extends HttpServlet implements Servlet {

    private ConfigurableApplicationContext webContext;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        processRequest(req, resp);

    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HandlerMapping handlerMapping = webContext.getBean(HandlerMapping.class);
        //String requestURL = req.getRequestURL().toString();
        //String beanName = requestURL.substring(requestURL.lastIndexOf("/") + 1);
        String beanName = handlerMapping.beanNameFromRequest(req);
        MyController myController = (MyController) webContext.getBean(beanName);
        myController.handleRequest(req, resp);
    }

    @Override
    public void init() throws ServletException {
//        super.init();
        /*String webContextName = getInitParameter("contextConfigLocation");
        webContext = new ClassPathXmlApplicationContext(new String[]{webContextName});*/
        String webContextName = getInitParameter("contextConfigLocation");
        System.out.println("DispatcherServlet => init(): Web Context Name: " + webContextName);
        ConfigurableApplicationContext rootContext =
                (ConfigurableApplicationContext)getServletContext().getAttribute("context");
        webContext = new ClassPathXmlApplicationContext(new String[]{webContextName}, rootContext);
        getServletContext().setAttribute("webcontext", webContext);
        System.out.println("DispatcherServlet => init(): child context created!");
        initUserData();
    }

    private void initUserData() {
        TweetService tweetService = (TweetService) webContext.getBean("tweetService");
        User user1 = (User) webContext.getBean("user", "Douglas");
        Tweet tweetFromUser1 = tweetService.createTweet(user1, "Some text #1 from user1!");
        tweetService.addTweet(tweetFromUser1);
        tweetFromUser1 = tweetService.createTweet(user1, "Some text #2 from user1!");
        tweetService.addTweet(tweetFromUser1);
        tweetFromUser1 = tweetService.createTweet(user1, "Some text #3 from user1!");
        tweetService.addTweet(tweetFromUser1);

        User user2 = (User) webContext.getBean("user", "Michael");
        Tweet tweetFromUser2 = tweetService.createTweet(user2, "Some text #1 from user2!");
        tweetService.addTweet(tweetFromUser2);
        tweetFromUser2 = tweetService.createTweet(user2, "Some text #2 from user2!");
        tweetService.addTweet(tweetFromUser2);
        tweetFromUser2 = tweetService.createTweet(user2, "Some text #3 from user2!");
        tweetService.addTweet(tweetFromUser2);
    }


    @Override
    public void destroy() {
//        super.destroy();
        webContext.close();
    }
}
