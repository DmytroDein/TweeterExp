package twitter.dispatcher;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ConfigurableApplicationContext;
import twitter.domain.services.TweetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class HelloController implements MyController, BeanNameAware {

    private String beanName;


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ConfigurableApplicationContext webContext =
                (ConfigurableApplicationContext) request.getServletContext().getAttribute("context");

        TweetService tweetService = (TweetService)webContext.getBean("tweetService");

        try (PrintWriter out = response.getWriter()){
            //out.write("<p> hello from Hello controller!</p>");
            out.write("<b> Bean name: "+ beanName +  "<br/> Message: hello from Hello controller!</b><br/>");

            webPrintTweets(tweetService, out);
            webPrintContext(webContext, out);
        }
    }

    private void webPrintContext(ConfigurableApplicationContext webContext, PrintWriter out) {
        out.write("<br/><b>-------------------------------------------- webcontext ----------------------------------------<b/><br/>");
        out.write(Arrays.toString(webContext.getBeanDefinitionNames()));
    }

    private void webPrintTweets(TweetService tweetService, PrintWriter out) {
        out.write("<br/><b>======================== List of tweets: =======================</b><br/>");
        tweetService.findAll().forEach(e ->
            out.write("Tweet: " + e +  "<br/>")
        );
    }

    @Override
    public void setBeanName(String s) {
        beanName = s;
    }
}
