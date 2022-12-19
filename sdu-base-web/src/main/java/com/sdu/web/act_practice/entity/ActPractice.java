package com.sdu.web.act_practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("act_practice")
public class ActPractice {
    @TableId(type = IdType.AUTO)
    private Long practiceId;
    @TableField(exist = false)
    private Long stuId;
    @TableField(exist = false)
    private String stuName;
    private String stuNum;
    private String practiceType;
    private String practiceName;
    private String practiceTime;
    private String practiceResult;

    }
