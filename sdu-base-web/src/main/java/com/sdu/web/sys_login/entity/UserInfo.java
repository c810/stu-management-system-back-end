package com.sdu.web.sys_login.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/8 17:47
 */
@Data
public class UserInfo {
    private String name;
    private String avatar;  //https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif
    private String introduction;
    private Object[] roles;
}
