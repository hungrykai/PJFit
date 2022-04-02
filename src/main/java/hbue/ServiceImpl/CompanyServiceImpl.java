package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import hbue.Entity.Company;
import hbue.mapper.CompanyMapper;
import hbue.Service.ICompanyService;
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
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public IPage<Company> GetCompanyPage(int curpage, int pagesize, QueryWrapper queryWrapper, boolean selectall) {
        IPage<Company> companyIPage = new Page<>(curpage,pagesize,selectall);
        return companyMapper.selectPage(companyIPage,queryWrapper);
    }
}
