package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hbue.Entity.Userorjob_language;
import hbue.mapper.Job_welfareMapper;
import hbue.mapper.Userorjob_languageMapper;
import hbue.Service.IUserorjob_languageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YK
 * @since 2022-03-31
 */
@Service
public class Userorjob_languageServiceImpl extends ServiceImpl<Userorjob_languageMapper, Userorjob_language> implements IUserorjob_languageService {

    @Autowired
    private Userorjob_languageMapper userorjob_languageMapper;

    @Override
    public void SaveJobLanguage(Integer job_id, List<String> languagelist) {
        for (String langusge:languagelist){
            Userorjob_language userorjob_language = new Userorjob_language();
            userorjob_language.setJob_id(job_id);
            userorjob_language.setUser_language(langusge);
            userorjob_languageMapper.insert(userorjob_language);
        }
    }

    @Override
    public void SaveUserLanguages(Integer user_id, List<String> languagelist) {
        for (String langusge:languagelist){
            Userorjob_language userorjob_language = new Userorjob_language();
            userorjob_language.setUser_id(user_id);
            userorjob_language.setUser_language(langusge);
            userorjob_languageMapper.insert(userorjob_language);
        }
    }

    @Override
    public void DeleteJobLanguages(Integer job_id) {
        QueryWrapper<Userorjob_language> userorjob_languageQueryWrapper = new QueryWrapper<>();
        userorjob_languageQueryWrapper.eq("job_id",job_id);
        userorjob_languageMapper.delete(userorjob_languageQueryWrapper);
    }
}
