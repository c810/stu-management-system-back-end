package com.sdu.web.class_course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.web.class_course.entity.ClassCourse;
import com.sdu.web.class_course.entity.ClassCourseVo;
import com.sdu.web.class_course.entity.ParaListVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author Connor
 * @date 2022/11/7 23:46
 */
public interface ClassCourseMapper extends BaseMapper<ClassCourse> {
    IPage<ClassCourseVo> getCourseList(IPage<ClassCourseVo> page, @Param("para") ParaListVo para);
}

