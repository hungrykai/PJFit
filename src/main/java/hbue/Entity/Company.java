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
public class Company implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "company_id", type = IdType.AUTO)
      private Integer company_id;

    private String company_username;

    private String company_password;

    private String company_name;

    private String company_picture;

    private String company_introduction;

    private String company_location;

    private Integer company_number;


}
