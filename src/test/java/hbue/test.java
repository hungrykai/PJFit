package hbue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hbue.Entity.Company;
import hbue.Entity.Job;
import hbue.Entity.User;
import hbue.Service.ICompanyService;
import hbue.Service.IJobService;
import hbue.Service.IUserService;
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

    @Test
    public void test5(){
        List<Job> list = jobService.list();
        for (Job job:list){
            jobService.JudgeJobType(job);
        }
        for (Job job:list){
            System.out.println(job);
        }
    }
}

