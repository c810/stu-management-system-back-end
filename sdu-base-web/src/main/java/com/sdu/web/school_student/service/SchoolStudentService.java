package com.sdu.web.school_student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.school_student.entity.*;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/6 12:42
 */
public interface SchoolStudentService extends IService<SchoolStudent> {
    // 新增
    void addStu(SchoolStudent schoolStudent);
    // 编辑
    void editStu(SchoolStudent schoolStudent);
    // 根据用户id查询用户信息
    SchoolStudent getStuById(Long stuId);
    // 列表
    IPage<SchoolStudent> getList(StuPara para);
    // 删除
    void deleteStu(Long stuId);
    // 查询学生的课程
    IPage<MyCourseVo> getCourseList(CoursePara para);
    // 查询学生统计
    StuCount getStuCount();
    // 查询热门专业
    List<HotMajorVo> getHotMajor();
}
