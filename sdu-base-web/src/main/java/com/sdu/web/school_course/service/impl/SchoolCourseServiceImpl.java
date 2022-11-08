package com.sdu.web.school_course.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.school_course.entity.CourseListPara;
import com.sdu.web.school_course.entity.SchoolCourse;
import com.sdu.web.school_course.mapper.SchoolCourseMapper;
import com.sdu.web.school_course.service.SchoolCourseService;
import org.springframework.stereotype.Service;

/**
 * @author Connor
 * @date 2022/11/7 19:25
 */
@Service
public class SchoolCourseServiceImpl extends ServiceImpl<SchoolCourseMapper, SchoolCourse> implements SchoolCourseService {
    @Override
    public IPage<SchoolCourse> getCourseList(CourseListPara para) {
        // 构造分页对象
        IPage<SchoolCourse> page = new Page<>(para.getCurrentPage(),para.getPageSize());
        return this.baseMapper.getCourseList(page,para);
    }
}
