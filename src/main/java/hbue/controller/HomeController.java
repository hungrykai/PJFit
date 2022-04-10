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
        //得到首页公司列表的信息
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
        //各种工作的类别信息
        List<Integer> typelist = new ArrayList<>();
        String[] typename = {"后端开发","人工智能","移动开发","测试","运维","数据","前端开发","高端技术","架构师"};
        for (String type:typename){
            typelist.add(jobService.GetOneTypeJobs(type));
        }
        session.setAttribute("typelist",typelist);
        session.setAttribute("typenamelist",typename);
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
                 //若identity不为0，则代表为招聘人员，其identity为conpany_id，并查询
                 QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
                 companyQueryWrapper.eq("company_id",curuser.getUser_identity());
                 Company company = companyService.getOne(companyQueryWrapper);
                 session.setAttribute("curcompany",company);
                 return "forward:/home/index";
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

    //关于
    @RequestMapping("/about")
    public String GotoAbout(){
        return "fragments/about.html";
    }

    //FAQ
    @RequestMapping("/faq")
    public String GotoFaq(){
        return "fragments/faqs.html";
    }

    //联系
    @RequestMapping("/contact")
    public String Gotocontact(){
        return "fragments/contact.html";
    }


}
