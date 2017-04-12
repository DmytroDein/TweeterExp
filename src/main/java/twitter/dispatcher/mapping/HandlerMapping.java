package twitter.dispatcher.mapping;


import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {

    String beanNameFromRequest(HttpServletRequest request);
}
