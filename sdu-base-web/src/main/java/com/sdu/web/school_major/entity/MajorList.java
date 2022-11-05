package com.sdu.web.school_major.entity;

import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/5 22:22
 */
@Data
public class MajorList {
    private Long currentPage;
    private Long pageSize;
    private String collegeName;
    private String majorName;
}

