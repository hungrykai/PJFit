package hbue.Service;

import hbue.Entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
