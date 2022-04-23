package hbue.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hbue.Entity.Message;
import hbue.Service.IMessageService;
import hbue.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YK
 * @since 2022-03-30
 */

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;


    @Override
    public void SentAMessage(Integer from, Integer to, String content, LocalDateTime localDateTime, Integer read,Integer job_id) {
        Message message = new Message();
        message.setMessage_from(from);
        message.setMessage_to(to);
        message.setMessage_content(content);
        message.setMessage_date(localDateTime);
        message.setMessage_read(read);
        message.setMessage_job_id(job_id);
        messageMapper.insert(message);
    }

    @Override
    public List<Message> GetMessagelistByUser_id(Integer user_id) {
        QueryWrapper<Message> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper.eq("message_to",user_id);
        return messageMapper.selectList(messageQueryWrapper);
    }

    @Override
    public IPage<Message> GetMessagePage(int pagecurrent, int pagesize, QueryWrapper queryWrapper) {
        IPage<Message> messageIPage = new Page<>(pagecurrent,pagesize,true);
        return messageMapper.selectPage(messageIPage,queryWrapper);
    }

}
