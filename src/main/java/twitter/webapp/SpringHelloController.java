package twitter.webapp;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.Controller;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class SpringHelloController {
//public class SpringHelloController implements Controller {

//    @Override
//    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    @RequestMapping("/hello")
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try (PrintWriter out = response.getWriter()){
            out.write("<p> hello from Hello controller!</p>");
        }
        //return null;
    }
}
