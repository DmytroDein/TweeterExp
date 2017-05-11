package twitter.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/app/loginpage")
    public String UserLogin(){
//        return "./loginout/login";
        return "./loginout/login_css";
    }
}
