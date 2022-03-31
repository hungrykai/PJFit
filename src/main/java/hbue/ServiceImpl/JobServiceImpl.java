package hbue.ServiceImpl;

import hbue.Entity.Job;
import hbue.mapper.JobMapper;
import hbue.Service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        if (job.getJob_describe().contains("前端") || job.getJob_name().contains("前端研发")){
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
}
