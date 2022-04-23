package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hbue.Entity.User;
import hbue.Entity.UserAndJob;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public User GetUser(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email",email);
        //当前登陆者
        User curuser = getOne(queryWrapper);
        QueryWrapper<Userorjob_language> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id",curuser.getUser_id());
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
        boolean judge1;
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

    @Override
    public User GetUserById(Integer user_id) {
        User user = userMapper.selectById(user_id);
        //补充语言信息
        List<String> languages = new ArrayList<>();
        List<Userorjob_language> userorjob_languages = user_languageService.GetAllUserLanguages(user_id);
        for (Userorjob_language userorjob_language:userorjob_languages){
            languages.add(userorjob_language.getUser_language());
        }
        user.setUser_language(languages);
        return user;
    }

    //手动分页
    public IPage<UserAndJob> GetUserAndJobPage(List<UserAndJob> userAndJobs, IPage<UserAndJob> user_jobIPage){
        if (userAndJobs.size() % user_jobIPage.getSize() == 0){
            user_jobIPage.setPages(userAndJobs.size() / user_jobIPage.getSize());
        }
        user_jobIPage.setTotal(userAndJobs.size());
        long start = (user_jobIPage.getCurrent() - 1) * user_jobIPage.getSize();
        long end = user_jobIPage.getCurrent() * user_jobIPage.getSize();
        if (end > userAndJobs.size()){
            end = userAndJobs.size();
        }
        user_jobIPage.setRecords(userAndJobs.subList((int) start,(int) end));
        return user_jobIPage;
    }
}
