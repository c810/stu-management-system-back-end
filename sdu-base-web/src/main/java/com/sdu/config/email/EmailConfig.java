package com.sdu.config.email;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Connor
 * @date 2022/11/12 1:00
 * @description 邮箱配置
 */
@Data
@Component
public class EmailConfig {
    /**
     * 发件人邮箱
     */
    @Value("${spring.mail.username}")
    private String emailForm;
}
