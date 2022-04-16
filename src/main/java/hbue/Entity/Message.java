package hbue.Entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Message {

    private static final long serialVersionUID = 1L;

    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer message_id;

    private Integer message_from;

    private Integer message_to;

    private String message_content;

    private LocalDateTime message_date;

    //是否已经读过  0-未读  1-已读
    private Integer message_read;

    //消息相关联的job
    private Integer message_job_id;

}