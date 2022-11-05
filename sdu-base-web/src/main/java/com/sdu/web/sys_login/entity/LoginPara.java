package com.sdu.web.sys_login.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/4 22:09
 */
@Data
public class LoginPara {
    private String username;
    private String password;
    private String userType;
}
