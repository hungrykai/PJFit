package hbue.Entity;


import lombok.Data;

import java.util.List;

@Data
public class UserAndJobs {

    private User user;

    private List<Job> jobList;

}
