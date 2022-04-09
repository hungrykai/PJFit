package hbue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import hbue.Entity.Company;
import hbue.Entity.Job;
import hbue.Entity.JobAndCompany;
import hbue.Service.ICompanyService;
import hbue.Service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private IJobService jobService;

    @Autowired
    private ICompanyService companyService;

    //跳转到公司列表页面
    @RequestMapping("/companylist")
    public String companylist(HttpSession session){
        //准备公司列表数据
        IPage<Company> companylistIPage = companyService.GetCompanyPage(1, 12, null, true);
        session.setAttribute("companylistIPage",companylistIPage);
        return "fragments/company-list.html";
    }

    //跳转到公司简介页面
    //跳转到求职者面板个人资料
    @RequestMapping("/dashboard-company-profile")
    public String dashboardCandidateProfile(){
        return "fragments/dashboard-company-profile.html";
    }

    //公司分页
    @ResponseBody
    @RequestMapping("/getcompanypage")
    public IPage<Company> getcompanypage(@RequestParam Integer companycurrent, @RequestParam Integer companypagesize){
        return companyService.GetCompanyPage(companycurrent,companypagesize,null,true);
    }


}

