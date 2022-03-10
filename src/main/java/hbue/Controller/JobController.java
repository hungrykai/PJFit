package hbue.Controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YK
 * @since 2022-03-10
 */
@Controller
@RequestMapping("/job")
public class JobController {

    @RequestMapping("/joblist")
    public String joblist(){
        return "fragments/job-list.html";
    }

    @RequestMapping("/postjob")
    public String postjob(){
        return "";
    }


}

