package hbue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import hbue.Entity.Company;
import hbue.Entity.Job;
import hbue.Entity.JobAndCompany;
import hbue.Entity.User;
import hbue.Service.ICompanyService;
import hbue.Service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private IJobService jobService;

    @Autowired
    private ICompanyService companyService;

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

    //跳转到发布工作界面
    @RequestMapping("/dashboard-post-job")
    public String Gotodashboardpostjob(){
        return "fragments/dashboard-post-job.html";
    }

    //跳转到管理工作页面上去
    @RequestMapping("/dashboard-manage-job")
    public String Gotodashboardmanagejob(){
        return "fragments/dashboard-manage-job.html";
    }

    //跳转道所有申请者上面去
    @RequestMapping("/dashboard-applicants")
    public String Gotodashboardapplicants(){
        return "fragments/dashboard-applicants.html";
    }


    //跳转到消息上去
    @RequestMapping("/dashboard-messages")
    public String Gotodashboardmessages(){
        return "fragments/dashboard-messages.html";
    }

}

