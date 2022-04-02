package hbue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hbue.Entity.Company;
import hbue.Entity.Job;
import hbue.Entity.JobAndCompany;
import hbue.Service.ICompanyService;
import hbue.Service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String companylist(){
        return "fragments/company-list.html";
    }

    //跳转到公司简介页面
    //跳转到求职者面板个人资料
    @RequestMapping("/dashboard-company-profile")
    public String dashboardCandidateProfile(){
        return "fragments/dashboard-company-profile.html";
    }

/*
已优化删除
    */
/*根据compangy_id返回一个company*//*

    @ResponseBody
    @PostMapping("/getonecompany")
    public Company GetOneCompany(int company_id){
        QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
        companyQueryWrapper.eq("company_id",company_id);
        return companyService.getOne(companyQueryWrapper);
    }

*/
/*    //返回12个公司的详细信息以及其招聘的工作岗位
    public List<JobAndCompany> GetCompanyAndNum(){
        List<Company> records = companyService.GetCompanyPage(1, 12, null, false).getRecords();
        List<JobAndCompany> jobAndCompanies = new ArrayList<>();
        for (Company company:records){
            JobAndCompany jobAndCompany = new JobAndCompany();
            QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
            jobAndCompany.setCompany(company);
            jobQueryWrapper.eq("company_id",company.getCompany_id());
            jobAndCompany.setNumberOfjob(jobService.count(jobQueryWrapper));
            jobAndCompanies.add(jobAndCompany);
        }
        return jobAndCompanies;
    }

    */
}

