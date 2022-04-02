package hbue.Entity;


import lombok.Data;

@Data
public class JobAndCompany {

    private Job job;

    private Company company;
    //公司招聘岗位数
    private Integer NumberOfjob;

}
