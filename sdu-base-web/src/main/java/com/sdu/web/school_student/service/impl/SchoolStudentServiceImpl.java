package com.sdu.web.school_student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.school_student.entity.*;
import com.sdu.web.school_student.mapper.SchoolStudentMapper;
import com.sdu.web.school_student.service.SchoolStudentService;
import com.sdu.web.stu_role.entity.StuRole;
import com.sdu.web.stu_role.service.StuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Connor
 * @date 2022/11/6 12:47
 */
@Service
public class SchoolStudentServiceImpl extends ServiceImpl<SchoolStudentMapper, SchoolStudent> implements SchoolStudentService {
    @Autowired
    private StuRoleService stuRoleService;

    @Override
    @Transactional
    public void addStu(SchoolStudent schoolStudent) {
        // 新增学生
        int insert = this.baseMapper.insert(schoolStudent);
        // 设置学生的角色
        if (insert > 0) {
            StuRole stuRole = new StuRole();
            stuRole.setRoleId(schoolStudent.getRoleId());
            stuRole.setStuId(schoolStudent.getStuId());
            stuRoleService.save(stuRole);
        }
    }

    @Override
    @Transactional
    public void editStu(SchoolStudent schoolStudent) {
        // 编辑学生
        int update = this.baseMapper.updateById(schoolStudent);
        // 设置学生角色：先删除原来的，再插入新的
        if (update > 0) {
            // 先删除
            QueryWrapper<StuRole> query = new QueryWrapper<>();
            query.lambda().eq(StuRole::getStuId, schoolStudent.getStuId());
            stuRoleService.remove(query);
            // 插入
            StuRole stuRole = new StuRole();
            stuRole.setRoleId(schoolStudent.getRoleId());
            stuRole.setStuId(schoolStudent.getStuId());
            stuRoleService.save(stuRole);
        }
    }

    @Override
    public SchoolStudent getStuById(Long stuId) {
        return this.baseMapper.getStuById(stuId);
    }

    @Override
    public IPage<SchoolStudent> getList(StuPara para) {
        // 构造分页对象
        IPage<SchoolStudent> page = new Page<>(para.getCurrentPage(), para.getPageSize());
        return this.baseMapper.getList(page, para);
    }

    @Override
    @Transactional
    public void deleteStu(Long stuId) {
        // 删除学生
        int i = this.baseMapper.deleteById(stuId);
        if (i > 0) {
            QueryWrapper<StuRole> query = new QueryWrapper<>();
            query.lambda().eq(StuRole::getStuId, stuId);
            stuRoleService.remove(query);
        }
    }

    @Override
    public IPage<MyCourseVo> getCourseList(CoursePara para) {
        // 构造分页对象
        IPage<MyCourseVo> page = new Page<>(para.getCurrentPage(),para.getPageSize());
        return this.baseMapper.getCourseList(page,para);
    }

    @Override
    public StuCount getStuCount() {
        List<StuCountVo> stuCountVos = this.baseMapper.getStuCount();
        StuCount stuCount = new StuCount();
        List<Integer> count = new ArrayList<>();
        List<String> names = new ArrayList<>();
        if(stuCountVos.size() >0){
            for (int i=0;i<stuCountVos.size();i++){
                count.add(stuCountVos.get(i).getStuCount());
                names.add(stuCountVos.get(i).getClassYear());
            }
        }
        stuCount.setCount(count);
        stuCount.setNames(names);
        return stuCount;
    }

    @Override
    public List<HotMajorVo> getHotMajor() {
        return this.baseMapper.getHotMajor();
    }
}

