package hbue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hbue.Entity.Company;
import hbue.Entity.Job;
import hbue.Entity.JobAndCompany;
import hbue.Entity.UserAndCompany;
import hbue.Service.ICompanyService;
import hbue.Service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/job")
public class JobController {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IJobService jobService;

    @RequestMapping("/joblist")
    public String joblist(){
        return "fragments/job-list.html";
    }

    @RequestMapping("/postjob")
    public String postjob(){
        return "fragments/dashboard-post-job.html";
    }

    //返回6个岗位信息
    @ResponseBody
    @PostMapping("/getalllist")
    public List<JobAndCompany> getalllist(){
        List<JobAndCompany> jobAndCompanies = new ArrayList<>();
        List<Job> alllist = new ArrayList<>();
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id","1");
        alllist.addAll(jobService.GetPageJob(1,6,queryWrapper,false).getRecords());
        QueryWrapper<Job> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("company_id","3");
        alllist.addAll(jobService.GetPageJob(1,6,queryWrapper1,false).getRecords());
        QueryWrapper<Job> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("company_id","6");
        alllist.addAll(jobService.GetPageJob(1,6,queryWrapper2,false).getRecords());
        for (Job job:alllist){
            JobAndCompany jobAndCompany = new JobAndCompany();
            jobAndCompany.setJob(job);
            QueryWrapper<Company> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("company_id",job.getCompany_id());
            Company company = companyService.getOne(queryWrapper3);
            jobAndCompany.setCompany(company);
            jobAndCompanies.add(jobAndCompany);
        }
        return jobAndCompanies;
    }

    //返回有关开发语言的工作信息
    @ResponseBody
    @PostMapping("/getlanguagelist")
    public List<JobAndCompany> getLanguagelist(@RequestParam String user_language){
        List<Job> alllist = new ArrayList<>();
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id","1");
        alllist.addAll(jobService.GetPageJob(1,20,queryWrapper,false).getRecords());
        QueryWrapper<Job> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("company_id","3");
        alllist.addAll(jobService.GetPageJob(1,20,queryWrapper1,false).getRecords());
        QueryWrapper<Job> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("company_id","6");
        alllist.addAll(jobService.GetPageJob(1,20,queryWrapper2,false).getRecords());
        List<Job> jobs = new ArrayList<>();
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0 ; a + b + c  < 18 && i < alllist.size();i++){
            if (alllist.get(i).getCompany_id() == 1 && alllist.get(i).getJob_skill().indexOf(user_language)  != -1 && a < 6){
                a++;
                jobs.add(alllist.get(i));
            }
            if (alllist.get(i).getCompany_id() == 3 && alllist.get(i).getJob_skill().indexOf(user_language)  != -1 && b < 6){
                b++;
                jobs.add(alllist.get(i));
            }
            if (alllist.get(i).getCompany_id() == 6 && alllist.get(i).getJob_skill().indexOf(user_language)  != -1 && c < 6){
                c++;
                jobs.add(alllist.get(i));
            }
        }
        List<JobAndCompany> jobAndCompanies = new ArrayList<>();
        for (Job job:jobs){
            JobAndCompany jobAndCompany = new JobAndCompany();
            jobAndCompany.setJob(job);
            QueryWrapper<Company> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("company_id",job.getCompany_id());
            Company company = companyService.getOne(queryWrapper3);
            jobAndCompany.setCompany(company);
            jobAndCompanies.add(jobAndCompany);
        }
        return jobAndCompanies;
    }

}

