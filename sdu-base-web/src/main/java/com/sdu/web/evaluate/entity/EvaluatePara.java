package com.sdu.web.evaluate.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/13 23:35
 * @description
 */
@Data
public class EvaluatePara {
    private Long currentPage;
    private Long pageSize;
    private Long stuId;
    private String teacherName;
    private Long teacherId;
}
