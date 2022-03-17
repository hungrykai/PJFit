package hbue.Controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YK
 * @since 2022-03-10
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/candidateslist")
    public String joblist(){
        return "fragments/candidates-list.html";
    }

    @RequestMapping("/company-dashboard")
    public String companydashboard(){
        return "fragments/company-dashboard.html";
    }

    @RequestMapping("/candidate-dashboard")
    public String candidatedashboard(){
        return "fragments/candidate-dashboard.html";
    }






}

