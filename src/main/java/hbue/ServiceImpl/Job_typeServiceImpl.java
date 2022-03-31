package hbue.ServiceImpl;

import hbue.Entity.Job_type;
import hbue.mapper.Job_typeMapper;
import hbue.Service.IJob_typeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class Job_typeServiceImpl extends ServiceImpl<Job_typeMapper, Job_type> implements IJob_typeService {

}
