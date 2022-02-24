package hbue.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2022-02-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Job implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "job_id", type = IdType.AUTO)
      private Integer job_id;

    private String job_name;

    private Integer company_id;

    private Integer job_money;

    private String job_welfare;

    private Integer job_education;

    private String job_describe;

    private Integer job_number;

    private LocalDateTime job_time;

    private String job_type;


}
