package hbue.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YK
 * @since 2022-03-10
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

    private Integer user_job_state;


}
