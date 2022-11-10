package com.sdu.web.sys_login.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/9 20:44
 */
@Data
public class UpdatePasswordPara {
    private Long userId;
    private String userType;
    private String oldPassword;
    private String newPassword;
}
