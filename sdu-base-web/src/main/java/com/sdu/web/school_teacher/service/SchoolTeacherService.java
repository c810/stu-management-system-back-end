package com.sdu.web.school_teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.school_teacher.entity.SchoolTeacher;
import com.sdu.web.stu_points.entity.StuPoints;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/7 17:09
 */
public interface SchoolTeacherService extends IService<SchoolTeacher> {
    void addTeacher(SchoolTeacher teacher);
    void editTeacher(SchoolTeacher teacher);
    void deleteTeacher(Long teacherId);
    void savePoint(List<StuPoints> list, Long classId, Long courseId);
}
