package com.sdu.web.school_student.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Connor
 * @date 2022/11/10 14:33
 */
@Data
public class StuCount {
    List<Integer> count = new ArrayList<>();
    List<String> names = new ArrayList<>();
}

