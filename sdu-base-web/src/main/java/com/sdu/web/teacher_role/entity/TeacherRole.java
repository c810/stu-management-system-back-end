package com.sdu.web.teacher_role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/8 12:41
 */
@Data
@TableName("teacher_role")
public class TeacherRole {
    @TableId(type = IdType.AUTO)
    private Long teacherRoleId;
    private Long teacherId;
    private Long roleId;
}
