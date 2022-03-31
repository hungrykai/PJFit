package hbue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import hbue.Entity.*;
import hbue.Service.*;
import hbue.ServiceImpl.MailService;
import hbue.mapper.CompanyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {

    @Autowired
    private IUserorjob_languageService userorjob_languageService;

    @Autowired
    private IJob_typeService job_typeService;

    @Autowired
    private IJobService jobService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private IUserService userService;

    @Test
    public void test2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email","1099084595@qq.com");
        User user = userService.getOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void test3(){
        mailService.sendSimpleMail("1099084595@qq.com","哈哈哈哈哈哈","1");
    }


    @Test
    public void test4(){
        List<Company> list = companyService.list();
        System.out.println(list);
    }

    //将岗位分类
    @Test
    public void test5(){
        List<Job> list = jobService.list();
        for (Job job:list){
            jobService.JudgeJobType(job);
            for (String type : job.getJob_type()){
                Job_type job_type = new Job_type();
                job_type.setJob_id(job.getJob_id());
                job_type.setJob_type(type);
                job_typeService.saveOrUpdate(job_type);
            }
        }
    }

    @Test
    public void test6(){
        Job_type job_type = new Job_type();
        job_type.setId(1);
        job_type.setJob_id(1);
        job_type.setJob_type("后端开发");
        job_typeService.saveOrUpdate(job_type);
    }

    @Test
    public void test7(){
        Userorjob_language userorjob_language = new Userorjob_language();
        userorjob_language.setUser_language("Java");
        userorjob_language.setJob_id(1);
        userorjob_languageService.saveOrUpdate(userorjob_language);
    }

    //添加岗位所需要的语言
    @Test
    public void test8(){
        List<Job> list = jobService.list();
        for (Job job:list){
            jobService.JudgeJobLanguage(job);
            for (String type : job.getJob_skill()){
                Userorjob_language userorjob_language = new Userorjob_language();
                userorjob_language.setJob_id(job.getJob_id());
                userorjob_language.setUser_language(type);
                userorjob_languageService.saveOrUpdate(userorjob_language);
            }
        }
    }

    //测试返回的job信息是否完整
    @Test
    public void test9(){
        System.out.println(jobService.GetAllJobs());
    }

}

