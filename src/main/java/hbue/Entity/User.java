package hbue.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import hbue.Utils.AutoNameUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.codec.language.bm.Rule;
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
    /*    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")*/
    private LocalDate user_birthday;

    private String user_place;

    private String user_education;

    private String user_school;

    private String user_specialisms;

    private String user_experience;

    private String user_projects;

    private Integer user_expect_salary;

    private String user_expect_place;

    private String user_expect_type;

    @TableField(exist = false)
    private List<String> user_language;

    private String user_resume;

    public static User CreatAUser(int i){
        User user = new User();
        user.setUser_email("user"+i+"@qq.com");
        user.setUser_password("123");
        user.setUser_name(AutoNameUtil.autoSurAndName());
        if (Job_welfare.Random() / 50 == 0){
            user.setUser_gender("女");
        }else {
            user.setUser_gender("男");
        }
        user.setUser_picture("company-6.png");
        user.setUser_identity(0);
        user.setUser_phone(Integer.toString(Job_welfare.Random()));
        Integer educatiobn = Job_welfare.Random();
        if (educatiobn <=70){
            user.setUser_education("本科");
        }else if (educatiobn < 90){
            user.setUser_education("硕士");
        }else {
            user.setUser_education("博士");
        }
        user.setUser_expect_salary(Job_welfare.Random()/5);
        int city = Job_welfare.Random();
        if (city <15){
            user.setUser_expect_place("北京");
        }else if(city <30){
            user.setUser_expect_place("上海");
        }else if(city <60){
            user.setUser_expect_place("广州");
        }else if(city <90){
            user.setUser_expect_place("深圳");
        }else if(city <100){
            user.setUser_expect_place("武汉");
        }
        int job_type = Job_welfare.Random();
        if (job_type <30){
            user.setUser_expect_type("后端开发");
        }else if (job_type <38){
            user.setUser_expect_type("人工智能");
        }else if (job_type <45){
            user.setUser_expect_type("移动开发");
        }else if (job_type <55){
            user.setUser_expect_type("测试");
        }else if (job_type <63){
            user.setUser_expect_type("运维");
        }else if (job_type <70){
            user.setUser_expect_type("数据");
        }else if (job_type <78){
            user.setUser_expect_type("前端开发");
        }else if (job_type <95){
            user.setUser_expect_type("高端技术");
        }else{
            user.setUser_expect_type("架构师");
        }
        return user;
    }

    public static User addadmin(int i){
        User user = new User();
        user.setUser_email("admin"+(i+2)+"@qq.com");
        user.setUser_password("123");
        user.setUser_name(AutoNameUtil.autoSurAndName());
        if (Job_welfare.Random() / 50 == 0){
            user.setUser_gender("女");
        }else {
            user.setUser_gender("男");
        }
        user.setUser_picture("company-6.png");
        user.setUser_identity(i+4);
        user.setUser_phone(Integer.toString(Job_welfare.Random()));
        Integer educatiobn = Job_welfare.Random();
        if (educatiobn <=70){
            user.setUser_education("本科");
        }else if (educatiobn < 90){
            user.setUser_education("硕士");
        }else {
            user.setUser_education("博士");
        }
        return user;
    }


}
