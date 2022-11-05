package com.sdu.web.school_college.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/5 15:54
 * 接收分页参数
 */
@Data
public class ListPara {
    private Long currentPage;
    private Long pageSize;
    private String collegeName;
}
