package com.sdu.web.school_student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/6 12:38
 */
@Data
@TableName("school_student")
public class SchoolStudent {
    @TableId(type = IdType.AUTO)
    private Long stuId;
    @TableField(exist = false)
    private Long roleId;
    @TableField(exist = false)
    private Long collegeId;
    @TableField(exist = false)
    private Long majorId;
    private Long classId;
    private String stuName;
    private String sex;
    private String phone;
    private String intoTime;
    private String stuNum;
    private String password;
    @TableField(exist = false)
    private String majorName;
    @TableField(exist = false)
    private String collegeName;
    @TableField(exist = false)
    private String className;
}
