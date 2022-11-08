package com.sdu.web.course_teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/7 19:57
 */
@Data
@TableName("course_teacher")
public class CourseTeacher {
    @TableId(type = IdType.AUTO)
    private Long courseTeacherId;
    private Long teacherId;
    private Long courseId;
}
