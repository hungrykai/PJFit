package hbue.controller;


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
@RequestMapping("/company")
public class CompanyController {

    //跳转到公司列表页面
    @RequestMapping("/companylist")
    public String companylist(){
        return "fragments/company-list.html";
    }

    //跳转到公司简介页面
    //跳转到求职者面板个人资料
    @RequestMapping("/dashboard-company-profile")
    public String dashboardCandidateProfile(){
        return "fragments/dashboard-company-profile.html";
    }


}

