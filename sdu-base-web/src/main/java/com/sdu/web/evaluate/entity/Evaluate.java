package com.sdu.web.evaluate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Connor
 * @date 2022/11/13 22:43
 * @description
 */
@Data
@TableName("evaluate")
public class Evaluate {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer teacherId;
    private Integer teachLevel;
    private Integer homework;
    private Integer attitude;
    private Integer bookChosen;
    private Integer reaction;
    private String recommend;
    private String advice;
    private Date evaluateDate;
    private Long stuId;
}
