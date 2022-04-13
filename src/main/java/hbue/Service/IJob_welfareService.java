package hbue.Service;

import hbue.Entity.Job_welfare;
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
public interface IJob_welfareService extends IService<Job_welfare> {

    //批量保存工作福利
    public void SaveJob_Welfares(Integer job_id, List<String> job_welfare);

    //根据job_id批量删除工作福利
    public void DeleteJob_Welfares(Integer job_id);

}
