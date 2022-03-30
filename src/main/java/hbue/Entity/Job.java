package hbue.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YK
 * @since 2022-03-30
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

    private Integer job_money_low;

    private Integer job_money_high;

    private Integer job_employer_id;

    @TableField(exist = false)
    private List<String> job_type;

    private String job_education;

    private String job_describe;

    @TableField(exist = false)
    private List<String> job_skill;

    private String job_work_years;

    private String job_location;

    private String job_city;


}
