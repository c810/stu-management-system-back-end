package com.sdu.web.school_teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.school_student.entity.SchoolStudent;
import com.sdu.web.school_teacher.entity.SchoolTeacher;
import com.sdu.web.school_teacher.mapper.SchoolTeacherMapper;
import com.sdu.web.school_teacher.service.SchoolTeacherService;
import com.sdu.web.stu_points.entity.StuPoints;
import com.sdu.web.stu_points.service.StuPointsService;
import com.sdu.web.teacher_role.entity.TeacherRole;
import com.sdu.web.teacher_role.service.TeacherRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/7 17:09
 */
@Service
public class SchoolTeacherServiceImpl extends ServiceImpl<SchoolTeacherMapper, SchoolTeacher> implements SchoolTeacherService {
    @Autowired
    private TeacherRoleService teacherRoleService;
    @Autowired
    private StuPointsService stuPointsService;

    @Override
    @Transactional
    public void addTeacher(SchoolTeacher teacher) {
        // 保存教师
        int insert = this.baseMapper.insert(teacher);
        if (insert > 0) {
            // 设置教师的角色
            TeacherRole role = new TeacherRole();
            role.setRoleId(teacher.getRoleId());
            role.setTeacherId(teacher.getTeacherId());
            teacherRoleService.save(role);
        }
    }

    @Override
    @Transactional
    public void editTeacher(SchoolTeacher teacher) {
        int i = this.baseMapper.updateById(teacher);
        if (i > 0) {
            // 先删除原来的角色，再保存
            QueryWrapper<TeacherRole> query = new QueryWrapper<>();
            query.lambda().eq(TeacherRole::getTeacherId, teacher.getTeacherId());
            teacherRoleService.remove(query);
            // 再保存
            TeacherRole role = new TeacherRole();
            role.setRoleId(teacher.getRoleId());
            role.setTeacherId(teacher.getTeacherId());
            teacherRoleService.save(role);
        }
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        int i = this.baseMapper.deleteById(teacherId);
        if (i > 0) {
            // 删除对应的角色关联
            QueryWrapper<TeacherRole> query = new QueryWrapper<>();
            query.lambda().eq(TeacherRole::getTeacherId, teacherId);
            teacherRoleService.remove(query);
        }
    }

    @Override
    @Transactional
    public void savePoint(List<StuPoints> list, Long classId, Long courseId) {
        // 先删除
        QueryWrapper<StuPoints> query = new QueryWrapper<>();
        query.lambda().eq(StuPoints::getClassId,classId).eq(StuPoints::getCourseId,courseId);
        stuPointsService.remove(query);
        // 批量保存
        stuPointsService.saveBatch(list);
    }
}
