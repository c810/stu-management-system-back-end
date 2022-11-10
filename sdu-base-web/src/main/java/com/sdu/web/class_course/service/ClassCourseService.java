package com.sdu.web.class_course.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.class_course.entity.ClassCourse;
import com.sdu.web.class_course.entity.ClassCourseVo;
import com.sdu.web.class_course.entity.ParaListVo;
import com.sdu.web.class_course.entity.TeacherCourseVo;

/**
 * @author Connor
 * @date 2022/11/7 23:47
 */
public interface ClassCourseService extends IService<ClassCourse> {
    IPage<ClassCourseVo> getCourseList(ParaListVo para);
    IPage<ClassCourse> getTeacherCourse(TeacherCourseVo para);
}
