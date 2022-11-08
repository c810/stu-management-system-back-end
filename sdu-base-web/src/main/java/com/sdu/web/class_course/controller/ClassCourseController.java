package com.sdu.web.class_course.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.class_course.entity.ClassCourse;
import com.sdu.web.class_course.entity.ClassCourseVo;
import com.sdu.web.class_course.entity.ClassTree;
import com.sdu.web.class_course.entity.ParaListVo;
import com.sdu.web.class_course.service.ClassCourseService;
import com.sdu.web.course_teacher.entity.CourseTeacher;
import com.sdu.web.course_teacher.service.CourseTeacherService;
import com.sdu.web.school_class.entity.SchoolClass;
import com.sdu.web.school_class.service.SchoolClassService;
import com.sdu.web.school_course.entity.SchoolCourse;
import com.sdu.web.school_course.service.SchoolCourseService;
import com.sdu.web.school_major.entity.SchoolMajor;
import com.sdu.web.school_major.service.SchoolMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Connor
 * @date 2022/11/7 23:48
 */
@RestController
@RequestMapping("/api/classCourse")
public class ClassCourseController {
    @Autowired
    private SchoolMajorService schoolMajorService;
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private SchoolCourseService schoolCourseService;
    @Autowired
    private ClassCourseService classCourseService;
    @Autowired
    private CourseTeacherService courseTeacherService;

    // 查询班级树
    @GetMapping("/getClassTree")
    public ResultVo getClassTree() {
        // 查询所有专业
        QueryWrapper<SchoolMajor> query = new QueryWrapper<>();
        query.lambda().orderByAsc(SchoolMajor::getOrderNum);
        // 专业列表
        List<SchoolMajor> majorList = schoolMajorService.list(query);
        // 返回值
        List<ClassTree> vo = new ArrayList<>();
        if (majorList.size() > 0) {
            for (SchoolMajor schoolMajor : majorList) {
                ClassTree tree = new ClassTree();
                Random random = new Random();
                tree.setType("0");
                tree.setClassId(schoolMajor.getMajorId() + random.nextLong());
                tree.setClassName(schoolMajor.getMajorName());
                // 查询专业下面的班级
                QueryWrapper<SchoolClass> classQuery = new QueryWrapper<>();
                classQuery.lambda().eq(SchoolClass::getMajorId, schoolMajor.getMajorId());
                List<ClassTree> children = new ArrayList<>();
                List<SchoolClass> classList = schoolClassService.list(classQuery);
                for (SchoolClass schoolClass : classList) {
                    ClassTree treeVo = new ClassTree();
                    treeVo.setType("1");
                    treeVo.setClassId(schoolClass.getClassId());
                    treeVo.setClassName(schoolClass.getClassName());
                    children.add(treeVo);
                }
                tree.setChildren(children);
                vo.add(tree);
            }
        }
        return ResultUtils.success("查询成功!", vo);
    }

    // 查询课程列表
    @GetMapping("/getCourseList")
    public ResultVo getCourseList(){
        List<SchoolCourse> list = schoolCourseService.list();
        return ResultUtils.success("查询成功!",list);
    }

    // 分配课程保存
    @PostMapping("/saveCourse")
    public ResultVo saveCourse(@RequestBody List<ClassCourse> list){
        // TODO: 不知道为什么会服务器爆错
//        for(int i = 0; i < list.size(); i++){
//            QueryWrapper<CourseTeacher> query = new QueryWrapper<>();
//            query.lambda().eq(CourseTeacher::getCourseId,list.get(i).getCourseId());
//            CourseTeacher courseTeacher = courseTeacherService.getOne(query);
//            list.get(i).setTeacherId(courseTeacher.getTeacherId());
//        }
        classCourseService.saveBatch(list); // 批量保存
        return ResultUtils.success("分配成功!");
    }

    // 课程列表
    @GetMapping("/getClassCourse")
    public ResultVo getClassCourse(ParaListVo para){
        IPage<ClassCourseVo> list = classCourseService.getCourseList(para);
        return ResultUtils.success("查询成功!",list);
    }

    // 删除
    @DeleteMapping("/{classCourseId}")
    public ResultVo delete(@PathVariable("classCourseId") Long classCourseId){
        classCourseService.removeById(classCourseId);
        return ResultUtils.success("删除成功!");
    }
}
