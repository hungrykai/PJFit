package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hbue.Entity.User;
import hbue.Entity.User_job;
import hbue.mapper.User_jobMapper;
import hbue.Service.IUser_jobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YK
 * @since 2022-03-31
 */
@Service
public class User_jobServiceImpl extends ServiceImpl<User_jobMapper, User_job> implements IUser_jobService {
    @Autowired
    private User_jobMapper user_jobMapper;

    @Override
    public IPage<User_job> GetUser_JobPage(Integer pagecurrent, Integer pagesize, QueryWrapper queryWrapper) {
        IPage iPage = new Page(pagecurrent,pagesize,true);
        return user_jobMapper.selectPage(iPage,queryWrapper);
    }
}
