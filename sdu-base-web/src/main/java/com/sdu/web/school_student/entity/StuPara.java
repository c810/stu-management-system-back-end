package com.sdu.web.school_student.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/6 12:38
 */
@Data
public class StuPara {
    private Long currentPage;
    private Long pageSize;
    private String collegeName;
    private String collegeId;
    private String majorName;
    private String majorId;
    private String className;
    private String classId;
    private String stuName;
}
