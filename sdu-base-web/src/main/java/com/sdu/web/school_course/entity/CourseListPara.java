package com.sdu.web.school_course.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/7 19:17
 */
@Data
public class CourseListPara {
    private Long currentPage;
    private Long pageSize;
    private String courseName;
}
