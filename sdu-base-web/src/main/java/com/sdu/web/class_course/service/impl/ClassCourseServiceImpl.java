package com.sdu.web.class_course.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.class_course.entity.ClassCourse;
import com.sdu.web.class_course.entity.ClassCourseVo;
import com.sdu.web.class_course.entity.ParaListVo;
import com.sdu.web.class_course.mapper.ClassCourseMapper;
import com.sdu.web.class_course.service.ClassCourseService;
import org.springframework.stereotype.Service;

/**
 * @author Connor
 * @date 2022/11/7 23:47
 */
@Service
public class ClassCourseServiceImpl extends ServiceImpl<ClassCourseMapper, ClassCourse> implements ClassCourseService {
    @Override
    public IPage<ClassCourseVo> getCourseList(ParaListVo para) {
        // 构造分页对象
        IPage<ClassCourseVo> page = new Page<>(para.getCurrentPage(),para.getPageSize());
        return this.baseMapper.getCourseList(page,para);
    }
}
