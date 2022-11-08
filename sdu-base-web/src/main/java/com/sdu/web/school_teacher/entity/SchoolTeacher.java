package com.sdu.web.school_teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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

}
