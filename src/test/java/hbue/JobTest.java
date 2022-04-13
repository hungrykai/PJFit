package hbue;


import hbue.Service.IJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JobTest {
    @Autowired
    private IJobService jobService;

    //找出GetJobAndCompanyPage方法中size为60的bug
    @Test
    public void test1(){
        System.out.println(jobService.GetOneByJobId(15));
    }



}
