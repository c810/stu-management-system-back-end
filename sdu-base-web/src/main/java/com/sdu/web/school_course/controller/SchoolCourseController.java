package com.sdu.web.school_course.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.course_teacher.entity.CourseTeacher;
import com.sdu.web.course_teacher.service.CourseTeacherService;
import com.sdu.web.school_course.entity.CourseListPara;
import com.sdu.web.school_course.entity.SchoolCourse;
import com.sdu.web.school_course.service.SchoolCourseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Connor
 * @date 2022/11/7 19:26
 */
@RestController
@RequestMapping("/api/course")
public class SchoolCourseController {
    @Autowired
    private SchoolCourseService schoolCourseService;
    @Autowired
    private CourseTeacherService courseTeacherService;

    // 新增
    @PostMapping
    public ResultVo add(@RequestBody SchoolCourse schoolCourse){
        boolean save = schoolCourseService.save(schoolCourse);
        if(save){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody SchoolCourse schoolCourse){
        boolean save = schoolCourseService.updateById(schoolCourse);
        if(save){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    // 删除
    @DeleteMapping("/{courseId}")
    public ResultVo delete(@PathVariable("courseId") Long courseId){
        boolean save = schoolCourseService.removeById(courseId);
        if(save){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    // 列表
    /*@GetMapping("/list")
    public ResultVo list(CourseListPara para){
        // 分页对象
        IPage<SchoolCourse> page = new Page<>(para.getCurrentPage(),para.getPageSize());
        // 构造查询条件
        QueryWrapper<SchoolCourse> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(para.getCourseName())){
            query.lambda().like(SchoolCourse::getCourseName,para.getCourseName());
        }
        IPage<SchoolCourse> list = schoolCourseService.page(page, query);
        return ResultUtils.success("查询成功!",list);
    }*/
    // 列表
    @GetMapping("/list")
    public ResultVo list(CourseListPara para){
        IPage<SchoolCourse> list = schoolCourseService.getCourseList(para);
        return ResultUtils.success("查询成功!",list);
    }

    // 分配教师保存
    @PostMapping("/assignSave")
    public ResultVo assignSave(@RequestBody CourseTeacher courseTeacher){
        // 判断课程是否已经分配老师
        QueryWrapper<CourseTeacher> query = new QueryWrapper<>();
        query.lambda().eq(CourseTeacher::getCourseId,courseTeacher.getCourseId());
        CourseTeacher one = courseTeacherService.getOne(query);
        if(one != null){
            return ResultUtils.error("该课程已经分配老师，不用重复分配!");
        }
        QueryWrapper<CourseTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CourseTeacher::getTeacherId,courseTeacher.getTeacherId());
        CourseTeacher teacher = courseTeacherService.getOne(queryWrapper);
        if(teacher != null){
            return ResultUtils.error("该老师已经在教授其他课程，不用重复分配!");
        }
        boolean save = courseTeacherService.save(courseTeacher);
        if(save){
            return ResultUtils.success("分配成功!");
        }
        return ResultUtils.error("分配失败!");
    }

    //解除教师
    @PostMapping("/removeTeacher")
    public ResultVo removeTeacher(@RequestBody CourseTeacher courseTeacher){
        QueryWrapper<CourseTeacher> query = new QueryWrapper<>();
        query.lambda().eq(CourseTeacher::getCourseId,courseTeacher.getCourseId())
                .eq(CourseTeacher::getTeacherId,courseTeacher.getTeacherId());
        boolean remove = courseTeacherService.remove(query);
        if(remove){
            return ResultUtils.success("解除成功!");
        }
        return ResultUtils.error("解除失败!");
    }
}
