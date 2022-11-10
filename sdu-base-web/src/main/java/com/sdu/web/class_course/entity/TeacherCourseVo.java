package com.sdu.web.class_course.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/9 11:42
 */
@Data
public class TeacherCourseVo {
    private Long currentPage;
    private Long pageSize;
    private Long teacherId;
    private String className;
    private String courseName;
    private String courseYear;
}
