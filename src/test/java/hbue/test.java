package hbue;

import hbue.ServiceImpl.MailService;
import hbue.mapper.CompanyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private MailService mailService;

    @Test
    public void test2(){
        System.out.println(companyMapper.selectMaps(null));
    }

    @Test
    public void test3(){
        mailService.sendSimpleMail("1099084595@qq.com","哈哈哈哈哈哈","1");
    }

}

