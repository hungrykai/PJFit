package hbue.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import hbue.Entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YK
 * @since 2022-03-31
 */
public interface ICompanyService extends IService<Company> {

    //分页查询公司
    public IPage<Company> GetCompanyPage(int curpage, int pagesize, QueryWrapper queryWrapper, boolean selectall);

}
