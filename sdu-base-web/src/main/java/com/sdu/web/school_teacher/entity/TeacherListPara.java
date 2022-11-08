package com.sdu.web.school_teacher.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/7 17:07
 */
@Data
public class TeacherListPara {
    private Long currentPage;
    private Long pageSize;
    private String teacherName;
}
