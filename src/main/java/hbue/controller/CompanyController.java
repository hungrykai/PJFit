package hbue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hbue.Entity.*;
import hbue.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
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
        System.out.println(company);
        if (!uploadcompanypicture.isEmpty()){
            String imgFileName = System.currentTimeMillis() + uploadcompanypicture.getOriginalFilename().substring(uploadcompanypicture.getOriginalFilename().lastIndexOf('.'));
            //得到文件存放位置
            String saveImgFileName = fileUrl + imagePath + imgFileName;
            System.out.println(saveImgFileName);
            File dest = new File(saveImgFileName);
            company.setCompany_picture(imgFileName);
            System.out.println("imgFileName:"+company.getCompany_picture());
            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                //不在就创立
                dest.getParentFile().mkdirs();
            }
            try {
                uploadcompanypicture.transferTo(dest);
                System.out.println(company);
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
    @RequestMapping("/company-job-resume")
    public String Gotocompanyjobresume(HttpSession session){
        List<JobAndUsers> jobAndUsers = new ArrayList<>();
        User curuser = (User) session.getAttribute("curuser");
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.eq("job_employer_id",curuser.getUser_id());
        jobQueryWrapper.select().orderByDesc("job_id");
        //保存工作信息
        IPage<Job> jobIPage = jobService.GetJobPageByQueryWrapper(1, 100, jobQueryWrapper, true);
        System.out.println(jobIPage.getRecords());
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
        System.out.println(jobAndUsers);
        session.setAttribute("jobAndUsers",jobAndUsers);
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
        IPage<Job> jobIPage = jobService.GetJobPageByQueryWrapper(1, 100, jobQueryWrapper, true);
        session.setAttribute("managejobpage",jobIPage);
        return "fragments/dashboard-manage-job.html";
    }

    //跳转道所有申请者上面去
    @RequestMapping("/dashboard-applicants")
    public String Gotodashboardapplicants(){
        return "fragments/dashboard-applicants.html";
    }

    //跳转到入围简历去
    @RequestMapping("/company-dashboard-shortlisted-resume")
    public String Gotocompanydashboardshortlistedresume(){
        return "fragments/company-dashboard-shortlisted-resume.html";
    }

    //跳转到消息上去
    @RequestMapping("/dashboard-messages")
    public String Gotodashboardmessages(){
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
        System.out.println(job);
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
    @RequestMapping("/company-job-resume-detail/{job_id}")
    public String Gotocompanyjobresumedetail(@PathVariable("job_id") Integer job_id,HttpSession session){
        JobAndUsers jobAndUser = new JobAndUsers();
        System.out.println(job_id);
        Job job = jobService.GetOneByJobId(job_id);
        System.out.println(job);
        jobAndUser.setJob(job);
        //得到公司
        User curuser = (User) session.getAttribute("curuser");
        Company company = companyService.getById(curuser.getUser_identity());
        jobAndUser.setCompany(company);
        QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
        user_jobQueryWrapper.eq("job_id",job.getJob_id());
        user_jobQueryWrapper.select().orderByDesc("user_job_time");
        List<User_job> user_jobList = user_jobService.list(user_jobQueryWrapper);
        jobAndUser.setUser_jobs(user_jobList);
        List<User> userList = new ArrayList<>();
        for (User_job user_job:user_jobList){
            userList.add(userService.getById(user_job.getUser_id()));
        }
        //存储List<User>
        jobAndUser.setUserList(userList);
        //得到收藏数
        jobAndUser.CountNumber();
        //得到投递数
        jobAndUser.CountSubmitNum();
        //得到最近投递时间
        jobAndUser.RecevieResumeTime();
        //存job数据
        session.setAttribute("jobAndUser",jobAndUser);
        return "fragments/company-job-resume-detail.html";
    }

}