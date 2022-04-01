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
 * @since 2022-03-31
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Job_welfare implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer job_id;

    private String welfare;
/*
五险一金，补充医疗保险，定期体检，年终奖，股票期权，带薪年假，员工旅游，免费班车，餐补，交通补助，包吃，节日福利，住房补贴，零食下午茶，公仔周边活动，免费健身房，免费早晚餐
* */

    public static int Random(){
        int max=100,min=1;
        int ran2 = (int) (Math.random()*(max-min)+min);
        return ran2;
    }
}
