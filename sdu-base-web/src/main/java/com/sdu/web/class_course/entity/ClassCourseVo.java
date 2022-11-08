package com.sdu.web.class_course.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/8 10:41
 * 接收数据库的返回值
 */
@Data
public class ClassCourseVo {
    private Long classCourseId;
    private Long classId;
    private Long courseId;
    private String className;
    private String courseName;
    private String courseYear;
    private String type;
}
