package com.sdu.web.school_teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Connor
 * @date 2022/11/7 17:06
 */
@Data
@TableName("school_teacher")
public class SchoolTeacher {
    @TableId(type = IdType.AUTO)
    private Long teacherId;
    private String teacherName;
    private String sex;
    private String phone;
    private String teacherNum;
    private String password;
    @TableField(exist = false)
    private Long roleId;
    private Integer unreadMessage;
    private String intoTime;
    private String email;
    private String politicalFace;
    private String nativePlace;
    private Date birthday;
    private String nation;
    private String eduBackground;
    private String idCard;
    private String maritalStatus;
    private String selfDescribe;
    private String signature;
    private String location;
    private String collegeName;
    private String position;
}
