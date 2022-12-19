package com.sdu.web.evaluate.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/13 23:23
 * @description
 */
@Data
public class EvaluateVo {
    private Long teacherId;
    private String teacherName;
    private String courseName;
    private Long stuId;
}
