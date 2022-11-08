package com.sdu.web.class_course.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Connor
 * @date 2022/11/7 23:45
 */
@Data
public class ClassTree {
    private Long classId;
    private String className;
    private String type;
    private String open;
    private List<ClassTree> children = new ArrayList<>();
}
