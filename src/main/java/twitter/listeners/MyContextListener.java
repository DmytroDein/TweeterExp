package twitter.listeners;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("MyContextListener: context initialized...");
        String [] rootContextFileNames = servletContextEvent
                .getServletContext()
                .getInitParameter("contextConfigLocation").split(" ");
        ConfigurableApplicationContext rootContext = new ClassPathXmlApplicationContext(rootContextFileNames);
        servletContextEvent.getServletContext().setAttribute("context", rootContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("MyContextListener: context destroyed...");
    }
}
