package hbue.Controller;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import hbue.Entity.User;
import hbue.ServiceImpl.MailService;
import hbue.ServiceImpl.UserServiceImpl;
import hbue.Utils.VerCodeGenerateUtil;
import org.apache.logging.log4j.message.ReusableMessage;
import org.apache.poi.openxml4j.opc.internal.unmarshallers.PackagePropertiesUnmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;


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
    private MailService mailService;

    @Autowired
    private UserServiceImpl userService;

    //首页
    @RequestMapping("/index")
    public String index(){
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
    public String userregister(String role, String email, String password){
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
        return "fragments/index.html";
    }

    @RequestMapping("/resetpwd")
    public String resetpwd(String email, String password){
        User user = new User();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_email",email);
        user.setUser_password(password);
        userService.update(user,updateWrapper);
        return "fragments/login.html";
    }

}
