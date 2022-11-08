package com.sdu.web.class_course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/7 23:43
 */
@Data
@TableName("class_course")
public class ClassCourse {
    @TableId(type = IdType.AUTO)
    private Long classCourseId;
    private Long classId;
    private Long courseId;
    private Long teacherId;
    private String courseYear;
    private String type;
}
