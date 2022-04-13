package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hbue.Entity.*;
import hbue.Service.*;
import hbue.mapper.JobMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YK
 * @since 2022-03-30
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private IJob_welfareService job_welfareService;

    @Autowired
    private IJob_typeService job_typeService;

    @Autowired
    private IUserorjob_languageService userorjob_languageService;

    @Override
    public void JudgeJobType(Job job) {
        List<String> list = new ArrayList();
        if (job.getJob_describe().contains("开发") ||
        job.getJob_name().contains("开发") ){
            list.add("后端开发");
        }
        if (job.getJob_describe().contains("机器学习") || job.getJob_describe().contains("智能") ||
        job.getJob_name().contains("机器学习") ||  job.getJob_name().contains("智能")){
            list.add("人工智能");
        }
        if (job.getJob_describe().contains("移动") || job.getJob_name().contains("移动")){
            list.add("移动开发");
        }
        if (job.getJob_describe().contains("测试")){
            list.add("测试");
        }
        if (job.getJob_describe().contains("运维") || job.getJob_name().contains("运维")){
            list.add("运维");
        }
        if (job.getJob_describe().contains("数据") || job.getJob_name().contains("数据")){
            list.add("数据");
        }
        if (job.getJob_describe().contains("前端") || job.getJob_describe().contains("Html") || job.getJob_describe().contains("CSS") ||
                job.getJob_describe().contains("html") || job.getJob_describe().contains("css") || job.getJob_describe().contains("Vue")||
                job.getJob_describe().contains("vue")){
            list.add("前端开发");
        }
        if (job.getJob_describe().contains("高端") || job.getJob_name().contains("高端")){
            list.add("高端技术");
        }
        if (job.getJob_describe().contains("架构") || job.getJob_name().contains("架构")){
            list.add("架构师");
        }
        job.setJob_type(list);
    }

    @Override
    public void JudgeJobLanguage(Job job) {
        List<String> list = new ArrayList();
        if (job.getJob_describe().contains("Java") || job.getJob_describe().contains("java") || job.getJob_describe().contains("JAVA")){
            list.add("Java");
        }
        if (job.getJob_describe().contains("C++") || job.getJob_describe().contains("c++")){
            list.add("C++");
        }
        if (job.getJob_describe().contains("C++") || job.getJob_describe().contains("c++")){
            list.add("C");
        }
        if (job.getJob_describe().contains("Python") || job.getJob_describe().contains("python")){
            list.add("Python");
        }
        if (job.getJob_describe().contains("JavaScript") || job.getJob_describe().contains("javascript")){
            list.add("JavaScript");
        }
        if (job.getJob_describe().contains("C#") || job.getJob_describe().contains("c#")){
            list.add("C#");
        }
        if (job.getJob_describe().contains("GO") || job.getJob_describe().contains("Go") ||
                job.getJob_describe().contains("go")){
            list.add("GO");
        }
        if (job.getJob_describe().contains("PHP") || job.getJob_describe().contains("php")){
            list.add("PHP");
        }
        if (job.getJob_describe().contains("Ruby") || job.getJob_describe().contains("ruby")){
            list.add("Ruby");
        }
        if (job.getJob_describe().contains("HTML") || job.getJob_describe().contains("html") || job.getJob_describe().contains("css")||
                job.getJob_describe().contains("CSS")){
            list.add("HTML");
        }
        if (job.getJob_describe().contains("SQL") || job.getJob_describe().contains("sql")){
            list.add("SQL");
        }
        if (job.getJob_describe().contains("Node") || job.getJob_describe().contains("node")){
            list.add("Node.js");
        }
        if (job.getJob_describe().contains("TypeScript") || job.getJob_describe().contains("typescript")){
            list.add("TypeScript");
        }
        if (job.getJob_describe().contains("Rust") || job.getJob_describe().contains("rust")){
            list.add("Rust");
        }
        if (job.getJob_describe().contains("Kotlin") || job.getJob_describe().contains("kotlin")){
            list.add("Kotlin");
        }
        job.setJob_skill(list);
    }

    @Override
    public Job GetOneJob(Job job) {
        //得到工作的类型
        List<String> types = new ArrayList<>();
        QueryWrapper<Job_type> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("job_id",job.getJob_id());
        List<Job_type> list1 = job_typeService.list(queryWrapper1);
        for (Job_type job_type:list1){
            types.add(job_type.getJob_type());
        }
        job.setJob_type(types);
        //设置工作的语言
        List<String> languages = new ArrayList<>();
        QueryWrapper<Userorjob_language> userorjob_languageQueryWrapper = new QueryWrapper<>();
        userorjob_languageQueryWrapper.eq("job_id",job.getJob_id());
        List<Userorjob_language> list2 = userorjob_languageService.list(userorjob_languageQueryWrapper);
        for (Userorjob_language userorjob_language:list2){
            languages.add(userorjob_language.getUser_language());
        }
        job.setJob_skill(languages);
        //设置工作福利
        List<String> welfare = new ArrayList<>();
        QueryWrapper<Job_welfare> job_welfareQueryWrapper = new QueryWrapper<>();
        job_welfareQueryWrapper.eq("job_id",job.getJob_id());
        List<Job_welfare> list3 = job_welfareService.list(job_welfareQueryWrapper);
        for (Job_welfare job_welfare:list3){
            welfare.add(job_welfare.getWelfare());
        }
        job.setJob_welfare(welfare);
        return job;
    }

    @Override
    public Job GetOneByJobId(Integer jobid) {
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.eq("job_id",jobid);
        Job job = getOne(jobQueryWrapper);
        job = GetOneJob(job);
        return job;
    }

    @Override
    public List<Job> GetAllJobs() {
        List<Job> list = list();
        for (Job job:list){
            GetOneJob(job);
        }
        return list;
    }

    @Override
    public IPage<Job> GetPageJob(int curpage, int size, QueryWrapper queryWrapper, boolean queryall) {
        //当前页面，页面大小，是否查询总界面数
        IPage<Job> jobIPage = new Page<>(curpage,size,queryall);
        jobIPage = jobMapper.selectPage(jobIPage,queryWrapper);
        //取出job信息进行其他字段的补充
        List<Job> records = jobIPage.getRecords();
        for (Job job:records){
            GetOneJob(job);
        }
        jobIPage.setRecords(records);
        return jobIPage;
    }

    @Override
    public Integer GetOneTypeJobs(String type) {
        QueryWrapper<Job_type> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_type",type);
        return job_typeService.count(queryWrapper);
    }

    @Override
    public IPage<JobAndCompany> GetJobAndCompanyPage(String jobtypename, Integer jobpagecurrent, Integer pagesize) {
        QueryWrapper<Job_type> job_typeQueryWrapper = new QueryWrapper<>();
        job_typeQueryWrapper.eq("job_type",jobtypename);
        IPage<Job_type> job_typeIPage = job_typeService.GetJobsByType(jobpagecurrent, pagesize, job_typeQueryWrapper, true);
        List<Job_type> job_types = job_typeIPage.getRecords();
        List<JobAndCompany> jobAndCompanyList = new ArrayList<>();
        for (Job_type job_type:job_types){
            JobAndCompany jobAndCompany = new JobAndCompany();
            Job job = GetOneByJobId(job_type.getJob_id());
            jobAndCompany.setJob(job);
            QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
            companyQueryWrapper.eq("company_id",job.getCompany_id());
            jobAndCompany.setCompany(companyService.getOne(companyQueryWrapper));
            jobAndCompanyList.add(jobAndCompany);
        }
        IPage<JobAndCompany> jobIPage = new Page<>(job_typeIPage.getCurrent(),job_typeIPage.getSize(),true);
        jobIPage.setRecords(jobAndCompanyList);
        jobIPage.setPages(job_typeIPage.getPages());
        jobIPage.setTotal(job_typeIPage.getTotal());
        return jobIPage;
    }

    @Override
    public JobAndCompany GetJobandCompanyById(Integer job_id) {
        JobAndCompany jobAndCompany = new JobAndCompany();
        Job job = GetOneByJobId(job_id);
        jobAndCompany.setJob(job);
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id",job.getCompany_id());
        jobAndCompany.setCompany(companyService.getOne(queryWrapper));
        return jobAndCompany;
    }

    @Override
    public IPage<Job> GetJobPageByQueryWrapper(Integer current, Integer pagesize, QueryWrapper queryWrapper, Boolean selectall) {
        //当前页面，页面大小，是否查询总界面数
        IPage<Job> jobIPage = new Page<>(current,pagesize,selectall);
        jobIPage = jobMapper.selectPage(jobIPage,queryWrapper);
        return jobIPage;
    }

    @Override
    public void DeleteJobById(Integer job_id) {
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        //先删除其对应的福利，语言，类别等
        job_welfareService.DeleteJob_Welfares(job_id);
        userorjob_languageService.DeleteJobLanguages(job_id);
        job_typeService.DeleteJob_Types(job_id);

        jobQueryWrapper.eq("job_id",job_id);
        jobMapper.delete(jobQueryWrapper);
    }

    @Override
    public Integer GetJob_id(Job job) {
        QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
        jobQueryWrapper.eq("job_name",job.getJob_name());
        jobQueryWrapper.eq("company_id",job.getCompany_id());
        jobQueryWrapper.eq("job_money_low",job.getJob_money_low());
        jobQueryWrapper.eq("job_money_high",job.getJob_money_high());
        jobQueryWrapper.eq("job_employer_id",job.getJob_employer_id());
        jobQueryWrapper.eq("job_education",job.getJob_education());
        jobQueryWrapper.eq("job_describe",job.getJob_describe());
        jobQueryWrapper.eq("job_work_years",job.getJob_work_years());
        return jobMapper.selectOne(jobQueryWrapper).getJob_id();
    }
}
