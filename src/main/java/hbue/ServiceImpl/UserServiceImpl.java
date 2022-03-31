package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hbue.Entity.User;
import hbue.Entity.Userorjob_language;
import hbue.Service.IUserorjob_languageService;
import hbue.mapper.UserMapper;
import hbue.Service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IUserorjob_languageService user_languageService;

    @Override
    public User GetUser(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email",email);
        //当前登陆者
        User curuser = getOne(queryWrapper);
        QueryWrapper<Userorjob_language> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id",curuser.getUser_id());
        queryWrapper1.eq("type",0);
        //查找出user精通语言
        List<Userorjob_language> user_languages = user_languageService.list(queryWrapper1);
        //进行封装
        List<String> map = new ArrayList();
        for (Userorjob_language user_language:user_languages ){
            map.add(user_language.getUser_language());
        }
        curuser.setUser_language(map);
        return curuser;
    }

    @Override
    public boolean UpdateUser(User user) {
        boolean judge1=true;
        boolean judge2=true;
        judge1 = updateById(user);
        Userorjob_language user_language = new Userorjob_language();
        user_language.setUser_id(user.getUser_id());
        for (int i = 0 ; i < user.getUser_language().size();i++){
            user_language.setUser_language(user.getUser_language().get(i));
            judge2 = user_languageService.saveOrUpdate(user_language);
        }
        return judge1&&judge2;
    }
}
