package com.sdu.web.school_course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.web.school_course.entity.CourseListPara;
import com.sdu.web.school_course.entity.SchoolCourse;
import org.apache.ibatis.annotations.Param;

/**
 * @author Connor
 * @date 2022/11/7 19:18
 */
public interface SchoolCourseMapper extends BaseMapper<SchoolCourse> {
    IPage<SchoolCourse> getCourseList(IPage<SchoolCourse> page, @Param("para") CourseListPara para);
}
