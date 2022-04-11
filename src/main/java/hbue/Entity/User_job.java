package hbue.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author YK
 * @since 2022-03-31
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class User_job implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "user_job_id", type = IdType.AUTO)
      private Integer user_job_id;

    private Integer user_id;

    private Integer job_id;
    //0-未投递， 1-已投递，2-已查看，3-已接收
    private Integer user_job_state;

    //0-未收藏，1-已收藏
    private Integer collect;

    //时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /*    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")*/
    private LocalDateTime user_job_time;

}
