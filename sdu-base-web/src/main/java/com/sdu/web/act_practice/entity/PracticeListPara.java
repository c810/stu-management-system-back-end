package com.sdu.web.act_practice.entity;

import lombok.Data;

@Data
public class PracticeListPara {
    private Long currentPage;
    private Long pageSize;
    private Long practiceId;
    private String practiceName;
    private String practiceType;
    private String practiceTime;
    private Long stuId;
    private String stuNum;
}
