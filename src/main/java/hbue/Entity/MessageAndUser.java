package hbue.Entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class MessageAndUser {

    private User user;

    private Job job;

    private Company company;

    private Message message;

}
