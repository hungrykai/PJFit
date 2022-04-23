package hbue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hbue.Entity.*;
import hbue.Service.*;
import hbue.ServiceImpl.MailService;
import hbue.mapper.CompanyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {

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
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.ge("job_id",471);
        jobQueryWrapper.le("job_id",580);
        List<Job> list = jobService.list(jobQueryWrapper);
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
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.ge("job_id",412);
        jobQueryWrapper.le("job_id",580);
        List<Job> list = jobService.list(jobQueryWrapper);
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

    }

    //添加岗位福利
    @Test
    public void testa(){
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.ge("job_id",412);
        jobQueryWrapper.le("job_id",580);
        List<Job> list = jobService.list(jobQueryWrapper);
        int num = 17;
        String welfare[] = {"五险一金","补充医疗保险","定期体检","年终奖","员工旅游","节日福利","股票期权","带薪年假","免费班车","餐补","交通补助",
        "包吃","住房补贴","零食下午茶","公仔周边活动","免费健身房","免费早晚餐"};
        for (Job job:list){
            for (int i = 0 ; i < num ; i++){
                if (i < 6){
                    Job_welfare job_welfare = new Job_welfare();
                    job_welfare.setWelfare(welfare[i]);
                    job_welfare.setJob_id(job.getJob_id());
                    job_welfareService.saveOrUpdate(job_welfare);
                }else {
                    if (Job_welfare.Random() / 75 == 1){
                        Job_welfare job_welfare = new Job_welfare();
                        job_welfare.setWelfare(welfare[i]);
                        job_welfare.setJob_id(job.getJob_id());
                        job_welfareService.saveOrUpdate(job_welfare);
                    }
                }
            }
        }
    }

    //测试job分页
    @Test
    public void testb(){
        IPage<Job> jobIPage = jobService.GetPageJob(5, 6, null,false);
        System.out.println(jobIPage.getTotal());
        System.out.println(jobIPage.getPages());
        System.out.println(jobIPage.getCurrent());
        System.out.println(jobIPage.getSize());
        List<Job> records = jobIPage.getRecords();
        for (Job job:records){
            System.out.println(job);
        }
    }

    //测试提供给前端页面的每个类别的工作
    public void testc(){

    }

    @Test
    //随机添加100个用户
    public void addusers(){
        for (int i = 0 ; i < 100 ; i++){
            userService.save(User.CreatAUser(i));
        }
    }

    //修改users的信息
    @Test
    public void updateusers(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.ge("user_id",5);
        userQueryWrapper.le("user_id",104);
        List<User> list = userService.list(userQueryWrapper);
        for (int i = 1 ; i < list.size() ; i++){
            int city = Job_welfare.Random();
            if (city <15){
                list.get(i).setUser_expect_place("北京");
            }else if(city <30){
                list.get(i).setUser_expect_place("上海");
            }else if(city <60){
                list.get(i).setUser_expect_place("广州");
            }else if(city <90){
                list.get(i).setUser_expect_place("深圳");
            }else if(city <100){
                list.get(i).setUser_expect_place("武汉");
            }
            int job_type = Job_welfare.Random();
            if (job_type <30){
                list.get(i).setUser_expect_type("后端开发");
            }else if (job_type <38){
                list.get(i).setUser_expect_type("人工智能");
            }else if (job_type <45){
                list.get(i).setUser_expect_type("移动开发");
            }else if (job_type <55){
                list.get(i).setUser_expect_type("测试");
            }else if (job_type <63){
                list.get(i).setUser_expect_type("运维");
            }else if (job_type <70){
                list.get(i).setUser_expect_type("数据");
            }else if (job_type <78){
                list.get(i).setUser_expect_type("前端开发");
            }else if (job_type <95){
                list.get(i).setUser_expect_type("高端技术");
            }else{
                list.get(i).setUser_expect_type("架构师");
            }
            list.get(i).setUser_email("user"+i+"@qq.com");
            userService.updateById(list.get(i));
        }
    }

    @Test
    //随机添加50个Hr
    public void addadmins(){
        for (int i = 0 ; i < 50 ; i++){
            userService.save(User.addadmin(i));
        }
    }

    @Test
    //添加投递关系，每个用户随机对岗位进行投投递，user_id从5-104
    public void AddUser_Jobs(){
        for (int i = 5 ; i <= 104 ; i++){
            for (int j = 0 ; j < 50 ; j++){
                //job_id为2-580随机
                int job_id = Job.Random();
                User_job user_job = new User_job();
                user_job.setUser_id(i);
                user_job.setJob_id(job_id);
                user_job.setUser_job_state(1);
                //随机确定是否收藏
                if (Job_welfare.Random() > 50){
                    user_job.setCollect(1);
                }else {
                    user_job.setCollect(0);
                }
                user_job.setUser_job_time(LocalDateTime.now());
                user_jobService.save(user_job);
            }
        }
    }

    @Test
    //给所有的工作添加雇主id
    public void Addemployerid(){
        List<Job> jobList = jobService.list();
        for (Job job:jobList){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_identity",job.getCompany_id());
            job.setJob_employer_id(userService.getOne(queryWrapper).getUser_id());
            jobService.updateById(job);
        }
    }

}

