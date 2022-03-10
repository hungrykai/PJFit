package hbue.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YK
 * @since 2022-02-25
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    //首页
    @RequestMapping("/index")
    public String index(){
        return "fragments/index.html";
    }

    //登陆界面
   @RequestMapping("/login")
    public String login(){
        return "fragments/login.html";
    }

    //注册
    @RequestMapping("/register")
    public String Register(){
        return "fragments/register.html";
    }

    //找回密码
    @RequestMapping("/reset")
    public String Reset(){
        return "fragments/reset.html";
    }


}
