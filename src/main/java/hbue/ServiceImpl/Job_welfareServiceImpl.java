package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hbue.Entity.Job_welfare;
import hbue.mapper.Job_welfareMapper;
import hbue.Service.IJob_welfareService;
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
public class Job_welfareServiceImpl extends ServiceImpl<Job_welfareMapper, Job_welfare> implements IJob_welfareService {

    @Autowired
    private Job_welfareMapper job_welfareMapper;

    @Override
    public void SaveJob_Welfares(Integer job_id, List<String> jobwelfares) {
        //保存工作福利
        for (String welfare:jobwelfares){
            Job_welfare job_welfare = new Job_welfare();
            job_welfare.setJob_id(job_id);
            job_welfare.setWelfare(welfare);
            job_welfareMapper.insert(job_welfare);
        }
    }

    @Override
    public void DeleteJob_Welfares(Integer job_id) {
        QueryWrapper<Job_welfare> job_welfareQueryWrapper = new QueryWrapper<>();
        job_welfareQueryWrapper.eq("job_id",job_id);
        job_welfareMapper.delete(job_welfareQueryWrapper);
    }
}
