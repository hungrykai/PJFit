package hbue.Entity;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class UserAndJob {

    @Autowired
    private User user;

    @Autowired
    private Company company;

    @Autowired
    private Job job;

    @Autowired
    private User_job user_job;


}
