package com.sdu.web.school_class.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/6 1:23
 */
@Data
public class SchoolClassPara {
    private Long currentPage;
    private Long pageSize;
    private String collegeName;
    private String majorName;
    private String className;
}
