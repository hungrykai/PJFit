package hbue.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import hbue.Entity.User;
import hbue.Entity.User_job;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YK
 * @since 2022-03-31
 */
public interface IUser_jobService extends IService<User_job> {

    //userjob分页
    public IPage<User_job> GetUser_JobPage(Integer pagecurrent, Integer pagesize, QueryWrapper queryWrapper);

}
