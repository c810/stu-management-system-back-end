package com.sdu.web.email.service.impl;

import com.sdu.config.email.EmailConfig;
import com.sdu.web.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @author Connor
 * @date 2022/11/12 1:07
 * @description 邮件业务层实例，实现对应接口方法
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件后端耗时明显更短，
     * @param sendTo 收件人地址
     * @param title  邮件标题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String sendTo, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailForm());
        message.setTo(sendTo);
        message.setSubject(title);
        message.setText(content);
        try{
            mailSender.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送Html邮件时间将会变长，但是需求更多（暂时带有附件的功能未添加）
     * @param sendTo 收件人地址
     * @param title 邮件标题
     * @param content 邮件内容
     */
    @Override
    public void sendHtmlMail(String sendTo, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try{
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailConfig.getEmailForm());
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
