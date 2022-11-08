package com.sdu.web.class_course.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/8 10:43
 * 接收查询列表的参数
 */
@Data
public class ParaListVo {
    private Long currentPage;
    private Long pageSize;
    private String courseName;
    private String courseYear;
    private String type;
    private String classId;}
