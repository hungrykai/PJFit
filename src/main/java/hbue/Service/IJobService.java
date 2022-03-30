package hbue.Service;

import hbue.Entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import hbue.Entity.User;

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

}
