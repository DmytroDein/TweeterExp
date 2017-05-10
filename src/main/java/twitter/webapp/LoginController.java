package twitter.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/app/loginp")
    public String UserLogin(){
        return "login";
    }
}
