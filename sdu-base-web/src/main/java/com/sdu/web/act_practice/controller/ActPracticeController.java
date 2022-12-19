package com.sdu.web.act_practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.act_practice.entity.ActPractice;
import com.sdu.web.act_practice.entity.PracticeListPara;
import com.sdu.web.act_practice.service.ActPracticeService;
import com.sdu.web.school_student.entity.SchoolStudent;
import com.sdu.web.school_student.service.SchoolStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Li Junda
 * @date 2022/11/13 15:34
 */
@RestController
@RequestMapping("/api/practice")
public class ActPracticeController {
    @Autowired
    private ActPracticeService actPracticeService;
    @Autowired
    private SchoolStudentService schoolStudentService;

    // 新增
    @PostMapping
    public ResultVo add(@RequestBody ActPractice actPractice){
        /*System.out.println("==============================================");
        System.out.println(actPractice.getStuNum());
        System.out.println(actPractice.getStuId());*/
        QueryWrapper<SchoolStudent> query = new QueryWrapper<>();
        if(actPractice.getStuId() != null ){
            query.lambda().eq(SchoolStudent::getStuId,actPractice.getStuId());

        }else{
            query.lambda().eq(SchoolStudent::getStuNum,actPractice.getStuNum());
        }
        SchoolStudent student = schoolStudentService.getOne(query);
        if(student == null){
            return ResultUtils.error("学生不存在,请检查学号!");
        }

        boolean save = actPracticeService.save(actPractice);
        if(save){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody ActPractice actPractice){
        QueryWrapper<SchoolStudent> query = new QueryWrapper<>();
        query.lambda().eq(SchoolStudent::getStuNum,actPractice.getStuNum());
        SchoolStudent student = schoolStudentService.getOne(query);
        if(student == null){
            return ResultUtils.error("学生不存在!");
        }
        boolean save = actPracticeService.updateById(actPractice);
        if(save){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    // 删除
    @DeleteMapping("/{practiceId}")
    public ResultVo delete(@PathVariable("practiceId") Long practiceId){
        boolean save = actPracticeService.removeById(practiceId);
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
    public ResultVo list(PracticeListPara para){
        System.out.println("=========================================================================");
        System.out.println(para.getStuId());
        /*QueryWrapper<SchoolStudent> query = new QueryWrapper<>();
        if(para.getStuId() != null ){
            query.lambda().eq(SchoolStudent::getStuId,para.getStuId());

        }else{
            query.lambda().eq(SchoolStudent::getStuNum,para.getStuNum());
        }
        SchoolStudent student = schoolStudentService.getOne(query);
        actPracticeService.getById(para.getStuId());*/
        IPage<ActPractice> list = actPracticeService.getPracticeList(para);
        return ResultUtils.success("查询成功!",list);
    }

/*    @GetMapping("/myList")
    public ResultVo myList(PracticeListPara para){
        IPage<ActPractice> list = actPracticeService.getPracticeList(para);
        return ResultUtils.success("查询成功!",list);
    }*/

}
