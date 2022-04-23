package hbue.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import hbue.Entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import hbue.Entity.UserAndJob;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YK
 * @since 2022-03-30
 */
public interface IUserService extends IService<User> {
    //根据email找到user，并补充List<String> user_language数据
    public User GetUser(String email);

    //保存user并保存List<String> user_language数据
    public boolean UpdateUser(User user);

    //根据userid得到一个完整的User
    public User GetUserById(Integer user_id);

    //手动分页
    public IPage<UserAndJob> GetUserAndJobPage(List<UserAndJob> userAndJobs, IPage<UserAndJob> user_jobIPage);
}
