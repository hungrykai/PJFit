package hbue.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import hbue.Entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import hbue.Entity.JobAndCompany;

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

    //根据jobid得到一个职位的所有信息
    public Job GetOneByJobId(Integer jobid);

    //返回所有工作的全部信息
    public List<Job> GetAllJobs();

    //分页查询工作信息
    public IPage<Job> GetPageJob(int curpage,int size, QueryWrapper queryWrapper,boolean queryall);

    //返回每种类型的工作数量
    public Integer GetOneTypeJobs(String type);

    //根据typename，current，pagesize返回一个jobpage
    public IPage<JobAndCompany> GetJobAndCompanyPage(String jobtypename, Integer jobpagecurrent, Integer pagesize);

    //根据jobid返回一个jobandcompany对象
    public JobAndCompany GetJobandCompanyById(Integer job_id);

    //根据条件查找其发布的所有工作分页（不填充工作福利，开发语言等相关字段）
    public IPage<Job> GetJobPageByQueryWrapper( Integer current,Integer pagesize, QueryWrapper queryWrapper,Boolean selectall);

    //根据job_id删除工作
    public void DeleteJobById(Integer job_id);

    //根据job的其他内容查找返回job——id
    public Integer GetJob_id(Job job);

}
