package com.sdu.web.sys_user.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/3 18:44
 * 参数接收类
 */
@Data
public class PagePara {
    private Long currentPage;
    private Long pageSize;
    private String phone;
    private String nickName;
}
