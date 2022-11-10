package com.sdu.web.school_student.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Connor
 * @date 2022/11/8 23:04
 */
@Data
public class MyCourseVo {
    private String courseName;
    private String teacherName;
    private String courseYear;
    private String type;
    private BigDecimal point;
}
