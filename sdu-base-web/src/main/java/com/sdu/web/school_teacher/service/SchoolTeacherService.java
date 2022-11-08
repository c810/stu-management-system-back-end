package com.sdu.web.school_teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.school_teacher.entity.SchoolTeacher;

/**
 * @author Connor
 * @date 2022/11/7 17:09
 */
public interface SchoolTeacherService extends IService<SchoolTeacher> {
    void addTeacher(SchoolTeacher teacher);
    void editTeacher(SchoolTeacher teacher);
    void deleteTeacher(Long teacherId);
}
