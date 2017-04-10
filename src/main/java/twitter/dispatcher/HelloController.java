package twitter.dispatcher;

import org.springframework.beans.factory.BeanNameAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloController implements MyController, BeanNameAware {

    private String beanName;


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()){
            out.write("<p> hello from Hello controller!</p>");
            out.write("<b>"+ beanName +  "hello from Hello controller!</b><br/>");
        }
    }

    @Override
    public void setBeanName(String s) {
        beanName = s;
    }
}
