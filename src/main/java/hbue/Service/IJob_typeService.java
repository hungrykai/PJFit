package hbue.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hbue.Entity.Job_type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YK
 * @since 2022-03-31
 */
public interface IJob_typeService extends IService<Job_type> {

    //根据工作类别查找工作
    public IPage<Job_type> GetJobsByType(int curpage, int pagesize, QueryWrapper queryWrapper, boolean selectall);

    /*批量保存工作类别*/
    public void SaveJob_Types(Integer job_id, List<String> jobtypes);

    //批量删除工作类别
    public void DeleteJob_Types(Integer job_id);

}
