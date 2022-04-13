package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hbue.Entity.Job_type;
import hbue.mapper.Job_typeMapper;
import hbue.Service.IJob_typeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YK
 * @since 2022-03-31
 */
@Service
public class Job_typeServiceImpl extends ServiceImpl<Job_typeMapper, Job_type> implements IJob_typeService {

    @Autowired
    private Job_typeMapper job_typeMapper;

    @Override
    public IPage<Job_type> GetJobsByType(int curpage, int pagesize, QueryWrapper queryWrapper, boolean selectall) {
        IPage<Job_type> job_typeIPage = new Page<>(curpage,pagesize,selectall);
        IPage page = job_typeMapper.selectPage(job_typeIPage, queryWrapper);
        return page;
    }

    @Override
    public void SaveJob_Types(Integer job_id, List<String> jobtypes) {
        for (String type :jobtypes){
            Job_type job_type = new Job_type();
            job_type.setJob_id(job_id);
            job_type.setJob_type(type);
            job_typeMapper.insert(job_type);
        }
    }

    @Override
    public void DeleteJob_Types(Integer job_id) {
        QueryWrapper<Job_type> job_typeQueryWrapper = new QueryWrapper<>();
        job_typeQueryWrapper.eq("job_id",job_id);
        job_typeMapper.delete(job_typeQueryWrapper);
    }
}
