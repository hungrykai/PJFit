package hbue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import hbue.Entity.*;
import hbue.Service.ICompanyService;
import hbue.Service.IJobService;
import hbue.Service.IJob_typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/job")
public class JobController {

    @Autowired
    private IJob_typeService job_typeService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IJobService jobService;

    @RequestMapping(value = {"/joblist/{typename}"})
    public String joblist(@PathVariable String typename, HttpSession session){
        IPage<JobAndCompany> jobIPage = jobService.GetJobAndCompanyPage(typename, 1, 10);
        session.setAttribute("jobIPage",jobIPage);
        session.setAttribute("typename",typename);
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

    //返回工作的分页信息
    @ResponseBody
    @RequestMapping(value = "/getjobpage", method = RequestMethod.GET)
    public IPage<JobAndCompany> getjobpage(@RequestParam("jobtypename") String jobtypename,@RequestParam("jobpagecurrent") Integer jobpagecurrent,@RequestParam("jobpagesize") Integer jobpagesize){
        return jobService.GetJobAndCompanyPage(jobtypename,jobpagecurrent,jobpagesize);
    }


    //跳转道岗位的详情页面
    @RequestMapping("/job_detail/{job_id}")
    public String GotoJobDetail(@PathVariable Integer job_id,HttpSession session){
        JobAndCompany jobAndCompany = jobService.GetJobandCompanyById(job_id);
        List<JobAndCompany> recommandjobs = getjobpage(jobAndCompany.getJob().getJob_type().get(0), 1, 3).getRecords();
        session.setAttribute("curjobandcompany",jobAndCompany);
        session.setAttribute("recommandjobs",recommandjobs);
        return "fragments/job-detail.html";
    }

}

