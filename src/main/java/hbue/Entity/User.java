package hbue.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @since 2022-03-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "user_id", type = IdType.AUTO)
      private Integer user_id;

    private String user_password;

    private String user_name;

    private String user_picture;

    private String user_gender;

    private Integer user_identity;

    private String user_phone;

    private String user_email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date user_birthday;

    private String user_place;

    private Integer user_education;

    private String user_school;

    private String user_specialisms;

    private String user_experience;

    private String user_projects;

    private Integer user_expect_salary;

    private String user_expect_place;

    private Integer user_expect_type;

    private List<String> user_language;

    private String user_resume;
}
