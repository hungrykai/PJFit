package hbue.ServiceImpl;

import hbue.Entity.Job;
import hbue.mapper.JobMapper;
import hbue.Service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
/*        if (job.getJob_describe().contains("后端开发") || job.getJob_describe().contains("后端研发")){
            job.setJob_type("后端开发");
        }
        else if (job.getJob_describe().contains("人工智能")){
            job.setJob_type("人工智能");
        }
        else if (job.getJob_describe().contains("移动开发")){
            job.setJob_type("移动开发");
        }
        else if (job.getJob_describe().contains("测试")){
            job.setJob_type("测试");
        }
        else if (job.getJob_describe().contains("运维")){
            job.setJob_type("运维");
        }
        else if (job.getJob_describe().contains("数据")){
            job.setJob_type("数据");
        }
        else if (job.getJob_describe().contains("前端开发") || job.getJob_describe().contains("前端研发")){
            job.setJob_type("前端开发");
        }
        else if (job.getJob_describe().contains("高端技术")){
            job.setJob_type("高端技术");
        }
        else if (job.getJob_describe().contains("架构师")){
            job.setJob_type("架构师");
        }
        else{
            job.setJob_type("其他技术");
        }*/
    }
}
