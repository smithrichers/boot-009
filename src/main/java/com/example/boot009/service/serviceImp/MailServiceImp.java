package com.example.boot009.service.serviceImp;

import com.example.boot009.entity.SimpleEmailEntity;
import com.example.boot009.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImp implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    //    获取发送方账户信息
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(SimpleEmailEntity simpleEmailEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(simpleEmailEntity.getSubject());
        message.setText(simpleEmailEntity.getContent());
        message.setTo(simpleEmailEntity.getTos());
        javaMailSender.send(message);
    }
}
