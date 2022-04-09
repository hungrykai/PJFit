package hbue;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hbue.Entity.Company;
import hbue.Entity.Job;
import hbue.Service.ICompanyService;
import hbue.Service.IJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CompanyTest {


    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IJobService jobService;

    @Test
    public void updateCompanyjob(){
        List<Company> list = companyService.list();
        for (Company company:list){
            QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("company_id",company.getCompany_id());
            company.setCompany_jobs(jobService.count(queryWrapper));
            companyService.saveOrUpdate(company);
        }
    }

}
