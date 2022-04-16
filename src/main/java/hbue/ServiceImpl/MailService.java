package hbue.ServiceImpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@Service
public class MailService {
    // Spring官方提供的集成邮件服务的实现类，目前是Java后端发送邮件和集成邮件服务的主流工具。
    @Resource
    private JavaMailSender mailSender;


    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 正文
     * @throws MessagingException
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1099084595@qq.com"); // 发件人
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}

