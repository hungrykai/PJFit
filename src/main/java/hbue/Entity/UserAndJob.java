package hbue.Entity;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
