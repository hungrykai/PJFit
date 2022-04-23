package hbue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hbue.Entity.*;
import hbue.Service.*;
import jnr.ffi.annotations.In;
import org.python.netty.handler.ipfilter.IpFilterRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
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

    //获取照片存放路径
    @Value("${filepathconfig.fileUrl}")
    private String fileUrl;

    @Value("${filepathconfig.imagePath}")
    private String imagePath;

    @Autowired
    private IUser_jobService user_jobService;

    @Autowired
    private IJob_welfareService job_welfareService;

    @Autowired
    private IUserorjob_languageService userorjob_languageService;

    @Autowired
    private IJob_typeService job_typeService;

    @Autowired
    private IJobService jobService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMessageService messageService;

    //跳转到公司面板
    @RequestMapping("/company-dashboard")
    public String companydashboard(){
        return "fragments/company-dashboard.html";
    }

    //跳转到公司列表页面
    @RequestMapping("/companylist")
    public String companylist(HttpSession session){
        //准备公司列表数据
        IPage<Company> companylistIPage = companyService.GetCompanyPage(1, 12, null, true);
        session.setAttribute("companylistIPage",companylistIPage);
        session.setAttribute("issearch",0);
        return "fragments/company-list.html";
    }

    //跳转到公司简介页面
    @RequestMapping("/dashboard-company-profile")
    public String dashboardCandidateProfile(HttpSession session){
        User curuser = (User) session.getAttribute("curuser");
        QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
        companyQueryWrapper.eq("company_id",curuser.getUser_identity());
        Company company = companyService.getOne(companyQueryWrapper);
        return "fragments/dashboard-company-profile.html";
    }

    //公司分页
    @ResponseBody
    @RequestMapping("/getcompanypage")
    public IPage<Company> getcompanypage(@RequestParam Integer companycurrent, @RequestParam Integer companypagesize){
        return companyService.GetCompanyPage(companycurrent,companypagesize,null,true);
    }

    /*companypictureupload*/
    //保存公司信息
    @ResponseBody
    @PostMapping("/companypictureupload")
    public Integer companypictureupload(Company company, @RequestParam("uploadcompanypicture") MultipartFile uploadcompanypicture, HttpSession session){
        Integer ok = 0;
        if (!uploadcompanypicture.isEmpty()){
            String imgFileName = System.currentTimeMillis() + uploadcompanypicture.getOriginalFilename().substring(uploadcompanypicture.getOriginalFilename().lastIndexOf('.'));
            //得到文件存放位置
            String saveImgFileName = fileUrl + imagePath + imgFileName;
            File dest = new File(saveImgFileName);
            company.setCompany_picture(imgFileName);
            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                //不在就创立
                dest.getParentFile().mkdirs();
            }
            try {
                uploadcompanypicture.transferTo(dest);
                companyService.updateById(company);
                //更新session
                session.setAttribute("curcompany",company);
                ok = 0;
            } catch (Exception e) {
                e.printStackTrace();
                ok = -1;
            }
        }else{
            companyService.updateById(company);
            Company company1 = companyService.getById(company.getCompany_id());
            //更新session
            session.setAttribute("curcompany",company1);
        }
        return ok;
    }

    //跳转到查看简历界面
    @RequestMapping({"/company-job-resume","/company-job-resume/{pagecurrent}/{pagesize}"})
    public String Gotocompanyjobresume(HttpSession session,@PathVariable(required = false) Integer pagecurrent,
                                       @PathVariable(required = false) Integer pagesize){
        List<JobAndUsers> jobAndUsers = new ArrayList<>();
        User curuser = (User) session.getAttribute("curuser");
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.eq("job_employer_id",curuser.getUser_id());
        jobQueryWrapper.select().orderByDesc("job_id");
        if (pagecurrent == null){
            pagecurrent = 1;
        }
        if (pagesize == null){
            pagesize = 10;
        }
        //保存工作信息
        IPage<Job> jobIPage = jobService.GetJobPageByQueryWrapper(pagecurrent, pagesize, jobQueryWrapper, true);
        IPage<JobAndUsers> jobAndUsersIPage = new Page<>(jobIPage.getCurrent(),jobIPage.getSize(),jobIPage.isSearchCount());
        for (Job job:jobIPage.getRecords()){
            JobAndUsers jobAndUser = new JobAndUsers();
            //查找job_id旗下投递的岗位信息并降序排
            QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
            user_jobQueryWrapper.eq("job_id",job.getJob_id());
            user_jobQueryWrapper.select().orderByDesc("user_job_time");
            List<User_job> user_jobList = user_jobService.list(user_jobQueryWrapper);
            jobAndUser.setUser_jobs(user_jobList);
            //得到收藏数
            jobAndUser.CountNumber();
            //得到投递数
            jobAndUser.CountSubmitNum();
            //得到最近投递时间
            jobAndUser.RecevieResumeTime();
            //存job数据
            jobAndUser.setJob(job);
            jobAndUsers.add(jobAndUser);
        }
        jobAndUsersIPage.setRecords(jobAndUsers);
        jobAndUsersIPage.setTotal(jobIPage.getTotal());
        jobAndUsersIPage.setPages(jobIPage.getPages());
        session.setAttribute("jobAndUsers",jobAndUsersIPage);
        return "fragments/company-job-resume.html";
    }

    //跳转到发布工作界面
    @RequestMapping("/dashboard-post-job")
    public String Gotodashboardpostjob(){
        return "fragments/dashboard-post-job.html";
    }

    //跳转到管理工作页面上去
    @RequestMapping("/dashboard-manage-job")
    public String Gotodashboardmanagejob(HttpSession session){
        User curuser = (User) session.getAttribute("curuser");
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.eq("job_employer_id",curuser.getUser_id());
        jobQueryWrapper.select().orderByDesc("job_id");
        IPage<Job> jobIPage = jobService.GetJobPageByQueryWrapper(1, 10, jobQueryWrapper, true);
        session.setAttribute("managejobpage",jobIPage);
        return "fragments/dashboard-manage-job.html";
    }

    //管理工作分页
    @RequestMapping("/dashboard-manage-job/{pagecurrent}/{pagesize}")
    public String GotodashboardmanagejobPage(HttpSession session,@PathVariable Integer pagecurrent,@PathVariable Integer pagesize){
        User curuser = (User) session.getAttribute("curuser");
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.eq("job_employer_id",curuser.getUser_id());
        jobQueryWrapper.select().orderByDesc("job_id");
        IPage<Job> jobIPage = jobService.GetJobPageByQueryWrapper(pagecurrent, pagesize, jobQueryWrapper, true);
        session.setAttribute("managejobpage",jobIPage);
        return "fragments/dashboard-manage-job.html";
    }
/*
    //跳转道所有申请者上面去
    @RequestMapping("/dashboard-applicants")
    public String Gotodashboardapplicants(HttpSession session){
        User curuser = (User) session.getAttribute("curuser");
        Company curcompany = (Company) session.getAttribute("curcompany");
        List<UserAndJobs> userAndJobsList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        //先查出公司旗下有哪些发布的job
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.eq("job_employer_id",curuser.getUser_id());
        List<Job> jobList = jobService.list(jobQueryWrapper);
        System.out.println(jobList);
        //在根据user_job找到求职者
        for (Job job:jobList){
            QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
            user_jobQueryWrapper.eq("job_id",job.getJob_id());
            List<User_job> user_jobList = user_jobService.list(user_jobQueryWrapper);
            System.out.println(user_jobList);
            for (User_job user_job:user_jobList){
                User user = userService.getById(user_job.getUser_id());
                //查找user
                if (userList.indexOf(user) == -1){
                    UserAndJobs userAndJobs = new UserAndJobs();
                    userAndJobs.setUser(user);
                    QueryWrapper<User_job> user_jobQueryWrapper1 = new QueryWrapper<>();
                    user_jobQueryWrapper1.eq("user_id",user.getUser_id());
                    List<User_job> list = user_jobService.list(user_jobQueryWrapper1);
                    //根据user_id找到求职者在这家公司的所有数据
                    for (User_job user_job1:list){
                        QueryWrapper<Job> jobQueryWrapper1 = new QueryWrapper<>();
                        jobQueryWrapper1.eq("job_id",user_job1.getJob_id());
                        jobQueryWrapper1.eq("job_employer_id",curuser.getUser_id());
                        userAndJobs.setJobList(jobService.list(jobQueryWrapper1));
                    }
                    userAndJobsList.add(userAndJobs);
                    userList.add(user);
                }
            }
        }
        System.out.println(userAndJobsList);
        session.setAttribute("allapplocants",userAndJobsList);
        return "fragments/dashboard-applicants.html";
    }*/

    //跳转到所有投递去
    @RequestMapping({"/company-dashboard-shortlisted-resume","/company-dashboard-shortlisted-resume/{pagecurrent}/{pagesize}/{type}"})
    public String Gotocompanydashboardshortlistedresume(HttpSession session, @PathVariable(required = false) Integer pagecurrent,
                                                        @PathVariable(required = false) Integer pagesize,@PathVariable(required = false) Integer type){
        User curuser = (User) session.getAttribute("curuser");
        Company curcompany = (Company) session.getAttribute("curcompany");
        List<UserAndJob> userAndJobList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        if (pagecurrent == null){
            pagecurrent = 1;
        }
        if (pagesize == null){
            pagesize = 10;
        }
        //先查出公司旗下有哪些发布的job
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.eq("job_employer_id",curuser.getUser_id());
        List<Job> jobList = jobService.list(jobQueryWrapper);
        //在根据user_job找到求职者
        for (Job job:jobList){
            QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
            user_jobQueryWrapper.eq("job_id",job.getJob_id());
            if (type == null || type == 0){
                user_jobQueryWrapper.ne("user_job_state",0);
                type = 0;
            }else {
                user_jobQueryWrapper.eq("user_job_state",type);
            }
            List<User_job> user_jobList = user_jobService.list(user_jobQueryWrapper);
            for (User_job user_job:user_jobList){
                //查找user
                UserAndJob userAndJob = new UserAndJob();
                userAndJob.setUser(userService.getById(user_job.getUser_id()));
                userAndJob.setCompany(curcompany);
                userAndJob.setUser_job(user_job);
                userAndJob.setJob(job);
                userAndJobList.add(userAndJob);
            }
        }
        IPage<UserAndJob> userAndJobIPage = new Page<>(pagecurrent,pagesize);
        userAndJobIPage.setTotal(userAndJobList.size());
        userAndJobIPage.setPages(userAndJobList.size() % pagesize == 0 ? userAndJobList.size()/pagesize : userAndJobList.size() /pagesize + 1);
        userAndJobIPage.setRecords(userAndJobList.subList((pagecurrent - 1) * pagesize,pagecurrent * pagesize > userAndJobList.size() ? userAndJobList.size() : pagecurrent * pagesize));
        session.setAttribute("passjobIPage",userAndJobIPage);
        session.setAttribute("companydashboard_shortlisted_resume_type",type);
        return "fragments/company-dashboard-shortlisted-resume.html";
    }


    //跳转到消息上去
    @RequestMapping({"/dashboard-messages","/dashboard-messages/{pagecurrent}/{pagesize}"})
    public String Gotodashboardmessages(HttpSession session,@PathVariable(required = false) Integer pagecurrent,
                                        @PathVariable(required = false) Integer pagesize){
        User curuser = (User) session.getAttribute("curuser");
        pagecurrent = pagecurrent == null ? 1 : pagecurrent;
        pagesize = pagesize == null ? 10 :pagesize;
        QueryWrapper<Message> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper.eq("message_to",curuser.getUser_id());
        IPage<Message> messageIPage = messageService.GetMessagePage(pagecurrent, pagesize, messageQueryWrapper);
        List<Message> messageList = messageIPage.getRecords();
        List<MessageAndUser> messageAndUsers = new ArrayList<>();
        for (Message message:messageList){
            MessageAndUser messageAndUser = new MessageAndUser();
            User user = userService.getById(message.getMessage_from());
            messageAndUser.setUser(user);
            messageAndUser.setJob(jobService.getById(message.getMessage_job_id()));
            messageAndUser.setMessage(message);
            messageAndUsers.add(messageAndUser);
        }
        IPage<MessageAndUser> messageAndUserIPage = new Page<>(pagecurrent,pagesize);
        messageAndUserIPage.setRecords(messageAndUsers);
        messageAndUserIPage.setTotal(messageIPage.getTotal());
        messageAndUserIPage.setPages(messageIPage.getPages());
        session.setAttribute("messageAndUsers",messageAndUserIPage);
        return "fragments/dashboard-messages.html";
    }

    //跳转到个人资料上去
    @RequestMapping("/dashboard-user-profile")
    public String Gotodashboarduserprofile(){
        return "fragments/dashboard-user-profile.html";
    }

    //发布一份工作招聘信息
    @RequestMapping("/postjob")
    public String postjob(Job job,HttpSession session){
        User curuser = (User) session.getAttribute("curuser");
        job.setJob_employer_id(curuser.getUser_id());
        job.setCompany_id(curuser.getUser_identity());
        jobService.save(job);
        Integer job_id = jobService.GetJob_id(job);
        //保存工作福利
        job_welfareService.SaveJob_Welfares(job_id,job.getJob_welfare());
        //保存工作所要求的语言
        userorjob_languageService.SaveJobLanguage(job_id,job.getJob_skill());
        //保存工作类别
        job_typeService.SaveJob_Types(job_id,job.getJob_type());

        return "redirect:dashboard-manage-job";
    }

    //修改发布的工作
    @RequestMapping("/dashboard-reset-job/{job_id}")
    public String Gotodashboardresetjob(@PathVariable("job_id") Integer job_id, HttpSession session){
        Job job = jobService.GetOneByJobId(job_id);
        session.setAttribute("curresetjob",job);
        return "fragments/dashboard-reset-job.html";
    }

    //修改发布的工作
    @RequestMapping("/submit-reset-job")
    public String submitresetjob(Job job, HttpSession session){
        User curuser = (User) session.getAttribute("curuser");
        job.setJob_employer_id(curuser.getUser_id());
        job.setCompany_id(curuser.getUser_identity());
        /*删除原来的工作相关*/
        job_welfareService.DeleteJob_Welfares(job.getJob_id());
        job_typeService.DeleteJob_Types(job.getJob_id());
        userorjob_languageService.DeleteJobLanguages(job.getJob_id());
        //保存工作福利
        job_welfareService.SaveJob_Welfares(job.getJob_id(),job.getJob_welfare());
        //保存工作所要求的语言
        userorjob_languageService.SaveJobLanguage(job.getJob_id(),job.getJob_skill());
        //保存工作类别
        job_typeService.SaveJob_Types(job.getJob_id(),job.getJob_type());
        //保存工作
        jobService.updateById(job);
        return "redirect:dashboard-manage-job";
    }

    //跳转到申请工作的人的详情页面上去
    @RequestMapping({"/company-job-resume-detail/{job_id}","/company-job-resume-detail/{job_id}/{pagecurrent}/{pagesize}"})
    public String Gotocompanyjobresumedetail(@PathVariable("job_id") Integer job_id,HttpSession session, @PathVariable(required = false) Integer pagecurrent,
                                             @PathVariable(required = false) Integer pagesize){
        JobAndUsers jobAndUser = new JobAndUsers();
        Job job = jobService.GetOneByJobId(job_id);
        jobAndUser.setJob(job);
        //得到公司
        User curuser = (User) session.getAttribute("curuser");
        Company company = companyService.getById(curuser.getUser_identity());
        jobAndUser.setCompany(company);
        QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
        user_jobQueryWrapper.eq("job_id",job.getJob_id());
        user_jobQueryWrapper.select().orderByDesc("user_job_time");
        if (pagecurrent == null){
            pagecurrent = 1;
        }
        if (pagesize == null){
            pagesize = 4;
        }
        IPage<User_job> user_jobIPage = user_jobService.GetUser_JobPage(pagecurrent,pagesize,user_jobQueryWrapper);
        List<User_job> user_jobList = user_jobIPage.getRecords();
        List<User> userList = new ArrayList<>();
        for (User_job user_job:user_jobList){
            user_job.setUser_job_state(2);
            Message message = new Message();
            message.setMessage_from(curuser.getUser_id());
            message.setMessage_to(user_job.getUser_id());
            message.setMessage_job_id(job_id);
            message.setMessage_date(LocalDateTime.now());
            message.setMessage_read(0);
            message.setMessage_content(company.getCompany_name()+"公司的Hr"+curuser.getUser_name()+"于"+message.getMessage_date()+"查看了您所投递其公司旗下的"+
                    job.getJob_name()+ "岗位，请注意邮件消息来电等！");
            messageService.save(message);
            user_jobService.updateById(user_job);
            userList.add(userService.GetUserById(user_job.getUser_id()));
        }
        //存储List<User_job>
        jobAndUser.setUser_jobs(user_jobList);
        //存储List<User>
        jobAndUser.setUserList(userList);
        //得到收藏数
        jobAndUser.CountNumber();
        //得到投递数
        jobAndUser.CountSubmitNum();
        //得到最近投递时间
        jobAndUser.RecevieResumeTime();
        //存job数据
        IPage<User> userIPage = new Page<>(pagecurrent,pagesize);
        userIPage.setPages(user_jobIPage.getPages());
        userIPage.setTotal(user_jobIPage.getTotal());
        userIPage.setRecords(userList);
        session.setAttribute("jobAndUser",jobAndUser);
        session.setAttribute("jobAndUserPage",userIPage);
        System.out.println(jobAndUser);
        return "fragments/company-job-resume-detail.html";
    }

    //跳转到申请者的详细界面
    @RequestMapping("/candidate-detail/{user_id}")
    public String Gotocandidatedetail(@PathVariable("user_id")Integer user_id,HttpSession session){
        User user = userService.GetUserById(user_id);
        session.setAttribute("showcuruser",user);
        return "fragments/candidates-single.html";
    }

    //跳转道公司详情页面去
    @RequestMapping("/company_detail/{company_id}")
    public String Gotocompanydetail(@PathVariable("company_id") Integer company_id,HttpSession session){
        //查找公司
        Company company = companyService.getById(company_id);
        //查找雇主
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_identity",company.getCompany_id());
        List<User> userlist = userService.list(userQueryWrapper);
        System.out.println(userlist);
        List<Job> jobList = new ArrayList<>();
        for (User user:userlist){
            QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
            jobQueryWrapper.eq("job_employer_id",user.getUser_id());
            jobList.addAll(jobService.list(jobQueryWrapper));
            if (jobList.size() > 3){
                break;
            }
        }
        for (Job job:jobList){
            jobService.GetOneJob(job);
        }
        System.out.println(jobList);
        session.setAttribute("viewcompany",company);
        session.setAttribute("companycommandjobs",jobList.subList(0,jobList.size()>3?3:jobList.size()));
        return "fragments/company-detail.html";
    }

    //公司分页请求
    //跳转到公司列表页面
    @RequestMapping("/companylist/{pagecurrent}/{pagesize}")
    public String companylist(HttpSession session,@PathVariable Integer pagecurrent,@PathVariable Integer pagesize){
        //准备公司列表数据
        IPage<Company> companylistIPage = companyService.GetCompanyPage(pagecurrent, pagesize, null, true);
        session.setAttribute("companylistIPage",companylistIPage);
        session.setAttribute("issearch",0);
        return "fragments/company-list.html";
    }

    //公司搜索请求
    @RequestMapping("/searchcompanylist")
    public String searchcompanylist(@RequestParam String field_name,@RequestParam String field_city,
                                    @RequestParam  String field_companynumber,HttpSession session){
        QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
        System.out.println(field_name+field_city+field_companynumber);
        if (field_name != null && !field_name.isEmpty()){
            companyQueryWrapper.like("company_name",field_name).or().like("company_introduction",field_name);
        }
        if (field_city != null && !field_city.isEmpty()){
            companyQueryWrapper.like("company_location",field_city);
        }
        if (field_companynumber.equals("已上市")){
            companyQueryWrapper.like("company_number",field_companynumber).or().like("company_number","C").or().
                    like("company_number","D");
        }
        if (field_companynumber.equals("未上市")){
            companyQueryWrapper.like("company_number","不需要融资");
        }
        IPage<Company> companyIPage = companyService.GetCompanyPage(1, 12, companyQueryWrapper, true);
        session.setAttribute("companylistIPage",companyIPage);
        session.setAttribute("searchcompany_name",field_name);
        session.setAttribute("searchcompany_number",field_companynumber);
        session.setAttribute("searchcompany_city",field_city);
        session.setAttribute("issearch",1);
        return "fragments/company-list.html";
    }

    //分页
    @RequestMapping("/searchcompanylist/{pagecurrent}/{pagesize}")
    public String searchcompanypage(HttpSession session,@PathVariable Integer pagecurrent,@PathVariable Integer pagesize){
        String field_name = (String) session.getAttribute("searchcompany_name");
        String field_city = (String) session.getAttribute("searchcompany_city");
        String field_companynumber = (String) session.getAttribute("searchcompany_number");
        QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
        if (field_name != null && !field_name.isEmpty()){
            companyQueryWrapper.like("company_name",field_name).or().like("company_introduction",field_name);
        }
        if (field_city != null && !field_city.isEmpty()){
            companyQueryWrapper.like("company_location",field_city);
        }
        if (field_companynumber.equals("已上市")){
            companyQueryWrapper.like("company_number",field_companynumber).or().like("company_number","C").or().
                    like("company_number","D");
        }
        if (field_companynumber.equals("未上市")){
            companyQueryWrapper.like("company_number","不需要融资");
        }
        IPage<Company> companyIPage = companyService.GetCompanyPage(pagecurrent, pagesize, companyQueryWrapper, true);
        session.setAttribute("companylistIPage",companyIPage);
        session.setAttribute("issearch",1);
        return "fragments/company-list.html";
    }

}