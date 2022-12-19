package com.sdu.web.email.service;

/**
 * @author Connor
 * @date 2022/11/12 1:03
 * @description 邮件服务接口，该实体用于用户重置密码
 */
public interface EmailService {
    /**
     * 发送简单邮件
     * @param sendTo 收件人地址
     * @param title  邮件标题
     * @param content 邮件内容
     */
    void sendSimpleMail(String sendTo, String title, String content);

    /**
     * 发送HTML邮件
     * @param sendTo 收件人地址
     * @param title 邮件标题
     * @param content 邮件内容
     */
    void sendHtmlMail(String sendTo, String title, String content);
}
