package twitter.dispatcher;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        String requestURL = req.getRequestURL().toString();
        String beanName = requestURL.substring(requestURL.lastIndexOf("/")+1);
        //PrintWriter printWriter = resp.getWriter();
        //printWriter.write(beanName);
        MyController myController = (MyController) webContext.getBean(beanName);
        myController.handleRequest(req, resp);
    }

    @Override
    public void init() throws ServletException {
//        super.init();
        String webContextName = getInitParameter("contextConfigLocation");
        webContext = new ClassPathXmlApplicationContext(new String[]{webContextName});

        //getServletContext().getInitParameter("contextConfigLocation").
    }

    @Override
    public void destroy() {
//        super.destroy();
        webContext.close();
    }
}
