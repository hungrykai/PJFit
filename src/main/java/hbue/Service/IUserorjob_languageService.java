package hbue.Service;

import hbue.Entity.Userorjob_language;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YK
 * @since 2022-03-31
 */
public interface IUserorjob_languageService extends IService<Userorjob_language> {

    //批量保存工作所需要的语言
    public void SaveJobLanguage(Integer job_id, List<String> languagelist);


    //批量保存user会的语言
    public void SaveUserLanguages(Integer user_id,List<String> languagelist);

    //批量删除工作所需语言
    public void DeleteJobLanguages(Integer job_id);

}
