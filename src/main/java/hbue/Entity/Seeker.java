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
public class Seeker implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "seeker_id", type = IdType.AUTO)
      private Integer seeker_id;

    private String seeker_usernmae;

    private String seeker_password;

    private String seeker_name;

    private String seeker_picture;

    private Integer seeker_gender;

    private Integer seeker_identity;

    private String seeker_phone;

    private String seeker_email;

    private LocalDateTime seeker_birthday;

    private String seeker_place;

    private Integer seeker_education;

    private String seeker_school;

    private String seeker_specialisms;

    private String seeker_experience;

    private String seeker_projects;

    private Integer seeker_expect_salary;

    private String seeker_expect_place;

    private Integer seeker_expect_type;

    private String Seeker_language;

    private Integer job_id;


}
