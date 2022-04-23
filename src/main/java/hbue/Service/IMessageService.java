package hbue.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import hbue.Entity.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface IMessageService extends IService<Message> {

    public void SentAMessage(Integer from, Integer to, String content, LocalDateTime localDateTime,Integer read,Integer job_id);

    //得到接收方为user——id的
    public List<Message> GetMessagelistByUser_id(Integer user_id);

    //消息分页
    public IPage<Message> GetMessagePage(int pagecurrent, int pagesize, QueryWrapper queryWrapper);

}
