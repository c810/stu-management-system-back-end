package com.sdu.web.course_teacher.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.course_teacher.entity.CourseTeacher;
import com.sdu.web.course_teacher.mapper.CourseTeacherMapper;
import com.sdu.web.course_teacher.service.CourseTeacherService;
import org.springframework.stereotype.Service;

/**
 * @author Connor
 * @date 2022/11/7 20:01
 */
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements CourseTeacherService {
}
