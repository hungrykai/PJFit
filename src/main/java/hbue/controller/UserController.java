package hbue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import hbue.Entity.*;
import hbue.Service.*;
import jnr.ffi.annotations.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@RequestMapping("/user")
public class UserController {

    //获取照片存放路径
    @Value("${filepathconfig.fileUrl}")
    private String fileUrl;

    @Value("${filepathconfig.imagePath}")
    private String imagePath;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUser_jobService user_jobService;

    @Autowired
    private IJobService jobService;


    //跳转到求职者面板
    @RequestMapping("/candidate-dashboard")
    public String candidatedashboard(){
        return "fragments/candidate-dashboard.html";
    }

    //跳转到求职者面板个人资料
    @RequestMapping("/dashboard-candidate-profile")
    public String dashboardCandidateProfile(){
        return "fragments/dashboard-candidate-profile.html";
    }

    //上传用户头像
    @ResponseBody
    @PostMapping("/pictureupload")
    /*@RequestParam("user_name") String user_name, @RequestParam("user_gender") String user_gender,
                                 @RequestParam("user_birthday") String user_birthday, @RequestParam("user_id") String user_id,
                                 @RequestParam("user_phone") String user_phone, @RequestParam("user_education") String user_education,
                                 @RequestParam("user_school") String user_school, @RequestParam("user_experience") String user_experience,
                                 @RequestParam("user_language") List<String> user_language, @RequestParam("user_place") String user_place,
                                 @RequestParam("user_projects") String user_projects, @RequestParam("user_specialisms") String user_specialisms,*/
    public Integer pictureupload( User user,@RequestParam("uploadpicture") MultipartFile uploadpicture, HttpSession session
                                 ){
        Integer ok = 0;
        if (!uploadpicture.isEmpty()) {
            //得到时间戳+文件名
            String imgFileName = System.currentTimeMillis() + uploadpicture.getOriginalFilename().substring(uploadpicture.getOriginalFilename().lastIndexOf('.'));
            //得到文件存放位置
            String saveImgFileName = fileUrl + imagePath + imgFileName;
            System.out.println(saveImgFileName);
            File dest = new File(saveImgFileName);
            user.setUser_picture(imgFileName);
            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                //不在就创立
                dest.getParentFile().mkdirs();
            }
            try {
                uploadpicture.transferTo(dest);
                userService.UpdateUser(user);
                //更新session
                session.setAttribute("curuser",user);
                ok = 0;
            } catch (Exception e) {
                e.printStackTrace();
                ok = -1;
            }
        }else {
            userService.UpdateUser(user);
            User user1 = userService.getById(user.getUser_id());
            //更新session
            session.setAttribute("curuser",user1);
        }
        return ok;
    }

    //获取user精通的语言传给前端
    @ResponseBody
    @RequestMapping("/getuserlanguage")
    public List<String> getuserlanguage(HttpSession session){
        User curuser = (User) session.getAttribute("curuser");
        return curuser.getUser_language();
    }

    //跳转到user仪表盘的简历界面
    @RequestMapping("/candidate-dashboard-resume")
    public String Gotocandidatedashboardresume(){
        return "fragments/candidate-dashboard-resume.html";
    }

    //跳转道user仪表盘的申请过的岗位里面
    @RequestMapping(path = {"/candidate-dashboard-applied-job","/candidate-dashboard-applied-job/{pagecurrent}/{pagesize}","/candidate-dashboard-applied-job/{pagecurrent}/{pagesize}/{type}"})
    public String Gotocandidatedashboardappliedjob(HttpSession session,@PathVariable(required = false) Integer pagecurrent,@PathVariable(required = false) Integer pagesize,
                                                   @PathVariable(required = false) Integer type){
        //查找user投递的简历
        User curuser = (User) session.getAttribute("curuser");
        QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
        user_jobQueryWrapper.eq("user_id",curuser.getUser_id());
        if (type != null && type != 0){
            user_jobQueryWrapper.eq("user_job_state",type);
        }
        if (type == null){
            type = 0;
        }
        if (pagecurrent == null){
            pagecurrent = 1;
        }
        if (pagesize == null){
            pagesize = 10;
        }
        IPage<User_job> userIPage = user_jobService.GetUser_JobPage(pagecurrent, pagesize, user_jobQueryWrapper);
        List<JobAndCompany> jobAndCompanyList = new ArrayList<>();
        for (User_job user_job :userIPage.getRecords()){
            JobAndCompany jobAndCompany = jobService.GetJobandCompanyById(user_job.getJob_id());
            jobAndCompany.setUser_job(user_job);
            jobAndCompanyList.add(jobAndCompany);
        }
        IPage<JobAndCompany> jobAndCompanyIPage = new Page<>(pagecurrent,pagesize);
        jobAndCompanyIPage.setRecords(jobAndCompanyList);
        jobAndCompanyIPage.setPages(userIPage.getPages());
        jobAndCompanyIPage.setTotal(userIPage.getTotal());
        session.setAttribute("appliedjobs",jobAndCompanyIPage);
        session.setAttribute("myappiledjobtype",type);
        return "fragments/candidate-dashboard-applied-job.html";
    }

    //跳转user仪表盘岗位提醒界面
    @RequestMapping("/candidate-dashboard-job-alerts")
    public String Gotocandidatedashboardjobalerts(HttpSession session){
        return "fragments/candidate-dashboard-job-alerts.html";
    }

    //推荐工作
    @ResponseBody
    @RequestMapping("/commandtenjobs")
    public List<CommandJob> commandJobs(HttpSession session){
        User curuser = (User) session.getAttribute("curuser");
        List<CommandJob> commandJobs = jobService.CommandJobs(curuser.getUser_id());
        for (CommandJob commandJob:commandJobs){
            Job job = jobService.getById(commandJob.getJob().getJob_id());
            commandJob.setJob(job);
            commandJob.setCompany(companyService.getById(job.getCompany_id()));
        }
        return commandJobs;
    }

    //跳转到user仪表盘里面的收藏的简历中
    @RequestMapping({"/candidate-dashboard-shortlisted-resume","/candidate-dashboard-shortlisted-resume/{pagecurrent}/{pagesize}"})
    public String Gotocandidatedashboardshortlistedresume(HttpSession session,@PathVariable(required = false) Integer pagecurrent,
                                                          @PathVariable(required = false) Integer pagesize){
        //查找user收藏的简历
        User curuser = (User) session.getAttribute("curuser");
        QueryWrapper<User_job> user_jobQueryWrapper = new QueryWrapper<>();
        if (pagecurrent == null){
            pagecurrent = 1;
        }
        if (pagesize == null){
            pagesize = 10;
        }
        user_jobQueryWrapper.eq("user_id",curuser.getUser_id());
        user_jobQueryWrapper.eq("collect",1);
        IPage<User_job> user_jobIPage = user_jobService.GetUser_JobPage(pagecurrent, pagesize, user_jobQueryWrapper);
        List<User_job> user_jobList =user_jobIPage.getRecords();
                List<JobAndCompany> jobAndCompanyList = new ArrayList<>();
        for (User_job user_job :user_jobList){
            JobAndCompany jobAndCompany = jobService.GetJobandCompanyById(user_job.getJob_id());
            jobAndCompany.setUser_job(user_job);
            jobAndCompanyList.add(jobAndCompany);
        }
        IPage<JobAndCompany> jobAndCompanyIPage = new Page<>(pagecurrent,pagesize);
        jobAndCompanyIPage.setRecords(jobAndCompanyList);
        jobAndCompanyIPage.setPages(user_jobIPage.getPages());
        jobAndCompanyIPage.setTotal(user_jobIPage.getTotal());
        session.setAttribute("collectjoblist",jobAndCompanyIPage);
        return "fragments/candidate-dashboard-shortlisted-resume.html";
    }

    //跳转到聊天消息界面
    @RequestMapping("/dashboard-messages")
    public String Gotodashboardmessages(HttpSession session){
        User curuser = (User) session.getAttribute("curuser");
        List<Message> messageList = messageService.GetMessagelistByUser_id(curuser.getUser_id());
        List<MessageAndUser> messageAndUsers = new ArrayList<>();
        for (Message message:messageList){
            MessageAndUser messageAndUser = new MessageAndUser();
            User user = userService.getById(message.getMessage_from());
            messageAndUser.setUser(user);
            messageAndUser.setCompany(companyService.getById(user.getUser_identity()));
            messageAndUser.setJob(jobService.getById(message.getMessage_job_id()));
            messageAndUser.setMessage(message);
            messageAndUsers.add(messageAndUser);
        }
        session.setAttribute("messageAndUsers",messageAndUsers);
        return "fragments/dashboard-messages.html";
    }

    //上传简历
    @ResponseBody
    @RequestMapping("/uploadresume")
    public Integer uploadresume(@RequestParam("uploadresume") MultipartFile uploadresume, HttpSession session){
        User user = (User) session.getAttribute("curuser");
        Integer ok = 0;
        if (!uploadresume.isEmpty()) {
            //得到时间戳+文件名
            String resumeFileName = uploadresume.getOriginalFilename();
            System.out.println(uploadresume.getOriginalFilename());
            //得到文件存放位置
            String saveImgFileName = fileUrl + imagePath + resumeFileName;
            System.out.println(saveImgFileName);
            File dest = new File(saveImgFileName);
            user.setUser_resume(resumeFileName);
            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                //不在就创立
                dest.getParentFile().mkdirs();
            }
            try {
                uploadresume.transferTo(dest);
                userService.UpdateUser(user);
                //更新session
                session.setAttribute("curuser",user);
                ok = 0;
            } catch (Exception e) {
                e.printStackTrace();
                ok = -1;
            }
        }
        userService.UpdateUser(user);
        return ok;
    }


    //修改密码
    @ResponseBody
    @RequestMapping("/changepassword")
    public Integer changepassword(@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword
                                  , HttpSession session){
        Integer result = 0;
        User curuser = (User) session.getAttribute("curuser");
        if (curuser.getUser_password().equals(oldpassword)){
            curuser.setUser_password(newpassword);
            userService.updateById(curuser);
            result = 0;
        }else {
            result = 1;
        }
        return result;
    }

}


