package com.sdu.web.sys_notice.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/10 0:07
 */
@Data
public class NoticeListPara {
    private Long currentPage;
    private Long pageSize;
    private String title;
}
