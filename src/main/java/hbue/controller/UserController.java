package hbue.controller;


import com.baomidou.mybatisplus.extension.service.IService;
import hbue.Entity.User;
import hbue.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private IUserService userService;

    //跳转到候选人列表
    @RequestMapping("/candidateslist")
    public String joblist(){
        return "fragments/candidates-list.html";
    }

    //跳转到公司面板
    @RequestMapping("/company-dashboard")
    public String companydashboard(){
        return "fragments/company-dashboard.html";
    }

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
        }
        userService.UpdateUser(user);
        return ok;
    }

    //获取user精通的语言传给前端
    @ResponseBody
    @PostMapping("/getuserlanguage")
    public List<String> getuserlanguage(HttpSession session){
        User curuser = (User) session.getAttribute("curuser");
        return curuser.getUser_language();
    }


}


