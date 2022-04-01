package hbue.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import hbue.Entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import hbue.Entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YK
 * @since 2022-03-30
 */
public interface IJobService extends IService<Job> {

    //判断工作的类型
    public void JudgeJobType(Job job);

    //判断工作所需要的语言
    public void JudgeJobLanguage(Job job);

    //补充单个工作的JobType和JobLanguage和jobwelfare
    public Job GetOneJob(Job job);

    //返回所有工作的全部信息
    public List<Job> GetAllJobs();

    //分页查询工作信息
    public IPage<Job> GetPageJob(int curpage,int size, QueryWrapper queryWrapper);
}
