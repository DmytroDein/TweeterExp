package twitter.dispatcher.mapping;


import javax.servlet.http.HttpServletRequest;

public class BeanNameFromURLhandlerMapping implements HandlerMapping{


    @Override
    public String beanNameFromRequest(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        return requestURL.substring(requestURL.lastIndexOf("/") + 1);
    }
}
