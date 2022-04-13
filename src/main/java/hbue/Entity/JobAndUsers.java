package hbue.Entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class JobAndUsers {
    //工作
    private Job job;
    //公司
    private Company company;
    //投递的简历用户
    private List<User> userList;
    //投递信息(包括收藏加投递)
    private List<User_job> user_jobs;
    //收藏数
    private Integer NumberOfCollect;
    //投递数
    private Integer SubmitResume;
    //最近接收时间
    private LocalDateTime ReceiveResume;

    //统计收藏数
    public void CountNumber(){
        NumberOfCollect = 0;
        if (!user_jobs.isEmpty()){
            for (User_job user_job:user_jobs){
                if (user_job.getCollect() == 1){
                    NumberOfCollect++;
                }
            }
        }
    }

    //统计投递数
    public void CountSubmitNum(){
        SubmitResume = 0;
        if (!user_jobs.isEmpty()){
            for (User_job user_job:user_jobs){
                if (user_job.getUser_job_state() != 0){
                    SubmitResume++;
                }
            }
        }
    }

    /*接收时间*/
    public void RecevieResumeTime(){
        ReceiveResume = null;
        if (!user_jobs.isEmpty() && user_jobs.get(0).getUser_job_time() != null){
            ReceiveResume = user_jobs.get(0).getUser_job_time();
        }
    }

}


