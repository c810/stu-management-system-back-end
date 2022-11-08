package com.sdu.web.school_course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/7 19:16
 */
@Data
@TableName("school_course")
public class SchoolCourse {
    @TableId(type = IdType.AUTO)
    private Long courseId;
    @TableField(exist = false)
    private Long teacherId;
    private String courseName;
    @TableField(exist = false)
    private String teacherName;
    private String courseDesc;
}
