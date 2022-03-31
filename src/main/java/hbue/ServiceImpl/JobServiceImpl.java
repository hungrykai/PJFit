package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hbue.Entity.Job;
import hbue.Entity.Job_type;
import hbue.Entity.Userorjob_language;
import hbue.Service.IJob_typeService;
import hbue.Service.IUserorjob_languageService;
import hbue.mapper.JobMapper;
import hbue.Service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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
}
