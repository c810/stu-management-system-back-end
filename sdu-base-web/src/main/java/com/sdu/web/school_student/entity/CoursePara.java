package com.sdu.web.school_student.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/8 23:04
 */
@Data
public class CoursePara {
    private Long currentPage;
    private Long pageSize;
    private Long stuId;
    private String courseName;
}
