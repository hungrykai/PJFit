package hbue.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import hbue.Entity.Company;
import hbue.Entity.Job;
import hbue.Entity.JobAndCompany;
import hbue.Entity.User;
import hbue.Service.ICompanyService;
import hbue.Service.IJobService;
import hbue.Service.IUserService;
import hbue.ServiceImpl.MailService;
import hbue.Utils.VerCodeGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
 * @since 2022-02-25
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IJobService jobService;

    @Autowired
    private MailService mailService;

    @Autowired
    private IUserService userService;

    //首页
    @RequestMapping("/index")
    public String index(HttpSession session){
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
        session.setAttribute("companies",jobAndCompanies);
        return "fragments/index.html";
    }

    //登陆界面
   @RequestMapping("/login")
    public String login(){
        return "fragments/login.html";
    }

    //注册
    @RequestMapping("/register")
    public String Register(){
        return "fragments/register.html";
    }

    //找回密码
    @RequestMapping("/reset")
    public String Reset(){
        return "fragments/reset.html";
    }

    //ajax获取验证码
    @ResponseBody
    @PostMapping("/getvercode")
    public int getvercode(@RequestParam String email, HttpSession session){
        String vercode = VerCodeGenerateUtil.generateVerCode();
        try {
            mailService.sendSimpleMail(email,"注册验证码","尊敬的用户,您好:\n"+"\n本次请求的邮件验证码为:" +
                    vercode + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n" +  "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");
            session.setAttribute("vercode",vercode);
            System.out.println("发送成功！");
            return 0;
        }catch (Exception e){
            return 1;
        }
    }

    //ajax检验验证码
    @ResponseBody
    @PostMapping("/checkvercode")
    public int checkvercode(@RequestParam String vercode,HttpSession session){
        System.out.println(vercode);
        System.out.println(session.getAttribute("vercode"));
        if (vercode.equals(session.getAttribute("vercode"))){
            return 0;
        }else {
            return 1;
        }
    }

    //0-求职者 1-招聘这
    @RequestMapping("/user-register")
    public String userregister(String role, String email, String password,HttpSession session){
        int identity;
        User user = new User();
        user.setUser_email(email);
        user.setUser_password(password);
        if (role.equals("0")){
            identity = 0;
        }else{
            identity = 1;
        }
        user.setUser_identity(identity);
        userService.save(user);
        session.setAttribute("curuser",user);
        return "fragments/index.html";
    }

    //忘记密码进行修改
    @RequestMapping("/resetpwd")
    public String resetpwd(String email, String password){
        User user = new User();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_email",email);
        user.setUser_password(password);
        userService.update(user,updateWrapper);
        return "fragments/login.html";
    }

    //登陆验证
    @RequestMapping("/userlogin")
    public String userlogin(String email, String password, Model model, HttpSession session){
         User curuser = userService.GetUser(email);
        System.out.println(curuser);
         if (curuser == null){
             model.addAttribute("login-error-message","当前账号暂未注册，请先注册！");
             return "fragments/login.html";
         }else {
             if (curuser.getUser_password().equals(password)){
                 session.setAttribute("curuser",curuser);
                 System.out.println("curuser:"+curuser);
                 return "fragments/index.html";
             }else {
                 model.addAttribute("curuser-email",email);
                 model.addAttribute("login-error-message","密码错误！");
                 return "fragments/login.html";
             }
         }
    }

    //退出
    @RequestMapping("/loginout")
    public String loginout(HttpSession session){
        session.invalidate();
        return "fragments/index.html";
    }


}
