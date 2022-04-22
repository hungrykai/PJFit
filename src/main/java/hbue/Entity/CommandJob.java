package hbue.Entity;

import lombok.Data;

@Data
public class CommandJob {

    private Job job;

    private Company company;

    private Double similar;

}
