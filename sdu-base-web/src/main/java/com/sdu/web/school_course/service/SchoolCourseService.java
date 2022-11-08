package com.sdu.web.school_course.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.school_course.entity.CourseListPara;
import com.sdu.web.school_course.entity.SchoolCourse;

/**
 * @author Connor
 * @date 2022/11/7 19:19
 */
public interface SchoolCourseService extends IService<SchoolCourse> {
    IPage<SchoolCourse> getCourseList(CourseListPara para);
}
