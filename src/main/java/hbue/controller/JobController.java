package hbue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import hbue.Entity.*;
import hbue.Service.ICompanyService;
import hbue.Service.IJobService;
import hbue.Service.IJob_typeService;
import hbue.Service.IUser_jobService;
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
    private IUser_jobService user_jobService;

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
        User_job user_job = new User_job();
        user_job = null;
        QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
        User curuser = (User) session.getAttribute("curuser");
        if (curuser != null){
            user_jobQueryWrapper.eq("user_id",curuser.getUser_id());
            user_jobQueryWrapper.eq("job_id",jobAndCompany.getJob().getJob_id());
            user_job = user_jobService.getOne(user_jobQueryWrapper);
        }
        session.setAttribute("user_job",user_job);
        return "fragments/job-detail.html";
    }

    //投递简历
    @ResponseBody
    @RequestMapping("/submitresume")
    public Integer submitresume(HttpSession session){
        Integer state = 0;
        User curuser = (User) session.getAttribute("curuser");
        if (curuser == null){
            state = 1;
            return state;
        }else {
            if (curuser.getUser_resume() == null || curuser.getUser_name() == null || curuser.getUser_gender() == null ||
            curuser.getUser_education() == null || curuser.getUser_experience() ==null || curuser.getUser_phone() == null ||
            curuser.getUser_specialisms() == null || curuser.getUser_place() == null){
                state = 2;
            }else {
                JobAndCompany jobAndCompany = (JobAndCompany) session.getAttribute("curjobandcompany");
                QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
                user_jobQueryWrapper.eq("user_id",curuser.getUser_id());
                user_jobQueryWrapper.eq("job_id",jobAndCompany.getJob().getJob_id());
                User_job user_job = user_jobService.getOne(user_jobQueryWrapper);
                if (user_job == null){
                    User_job newuser_job = new User_job();
                    newuser_job.setUser_id(curuser.getUser_id());
                    newuser_job.setJob_id(jobAndCompany.getJob().getJob_id());
                    newuser_job.setUser_job_state(1);
                    newuser_job.setCollect(0);
                    user_jobService.save(newuser_job);
                }else {
                    user_job.setUser_job_state(1);
                    user_jobService.updateById(user_job);
                }
            }
        }
        return state;
    }

    //取消/收藏工作
    @ResponseBody
    @RequestMapping("/collectjob")
    public Integer collectjob(HttpSession session){
        //收藏成功
        Integer State = 1;
        User curuser = (User) session.getAttribute("curuser");
        JobAndCompany jobAnddCompany = (JobAndCompany) session.getAttribute("curjobandcompany");
        if (curuser == null){
            //用户未登录
            State = 0;
        }else {
            QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
            user_jobQueryWrapper.eq("user_id",curuser.getUser_id());
            user_jobQueryWrapper.eq("job_id",jobAnddCompany.getJob().getJob_id());
            User_job user_job = user_jobService.getOne(user_jobQueryWrapper);
            if (user_job != null){
                if (user_job.getCollect() == 1){
                    //取消收藏
                    user_job.setCollect(0);
                    State = 2;
                }else{
                    user_job.setCollect(1);
                }
                user_jobService.updateById(user_job);
            }else {
                User_job user_job1 = new User_job();
                user_job1.setUser_id(curuser.getUser_id());
                user_job1.setJob_id(jobAnddCompany.getJob().getJob_id());
                user_job1.setUser_job_state(0);
                user_job1.setCollect(1);
                user_jobService.save(user_job1);
            }
        }
        return State;
    }

    //ajax请求工作工作所需要具备的语言,福利和工作类型
    @ResponseBody
    @RequestMapping("/getjoblanguage")
    public Job getuserlanguage(@RequestParam Integer job_id){
        System.out.println(job_id);
        return jobService.GetOneByJobId(job_id);
    }

    //ajax删除工作
    @ResponseBody
    @RequestMapping("/deletejob")
    public Integer DeleteJob(@RequestParam Integer job_id){
        jobService.DeleteJobById(job_id);
        return 1;
    }

}

