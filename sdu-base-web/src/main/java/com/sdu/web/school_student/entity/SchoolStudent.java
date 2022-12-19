package com.sdu.web.school_student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

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
    private String email;
    private String politicalFace;
    private String nativePlace;
    private Date birthday;
    private String nation;
    private String hobby;
    private String eduBackground;
    private String idCard;
    private String maritalStatus;
    private String selfDescribe;
    private String signature;
    private String location;
}
