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
@RequestMapping("/company")
public class CompanyController {

    @RequestMapping("/companylist")
    public String companylist(){
        return "fragments/company-list.html";
    }


}

