package com.sdu.web.school_teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.school_teacher.entity.SchoolTeacher;
import com.sdu.web.school_teacher.entity.TeacherListPara;
import com.sdu.web.school_teacher.service.SchoolTeacherService;
import com.sdu.web.sys_role.entity.SysRole;
import com.sdu.web.sys_role.service.SysRoleService;
import com.sdu.web.teacher_role.entity.TeacherRole;
import com.sdu.web.teacher_role.service.TeacherRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/7 17:10
 */
@RestController
@RequestMapping("/api/teacher")
public class SchoolTeacherController {
    @Autowired
    private SchoolTeacherService schoolTeacherService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private TeacherRoleService teacherRoleService;

    // 新增
    @PostMapping
    public ResultVo add(@RequestBody SchoolTeacher schoolTeacher){
        schoolTeacher.setPassword(DigestUtils.md5DigestAsHex(schoolTeacher.getPassword().getBytes()));
        schoolTeacherService.addTeacher(schoolTeacher);
        return ResultUtils.success("新增成功!");
    }

    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody SchoolTeacher schoolTeacher){
        schoolTeacherService.editTeacher(schoolTeacher);
        return ResultUtils.success("编辑成功!");
    }

    // 删除
    @DeleteMapping("/{teacherId}")
    public ResultVo delete(@PathVariable("teacherId") Long teacherId){
        schoolTeacherService.deleteTeacher(teacherId);
        return ResultUtils.success("删除成功!");
    }

    // 列表
    @GetMapping("/list")
    public ResultVo list(TeacherListPara para){
        // 构造分页对象
        IPage<SchoolTeacher> page = new Page<>(para.getCurrentPage(),para.getPageSize());
        // 构造查询条件
        QueryWrapper<SchoolTeacher> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(para.getTeacherName())){
            query.lambda().like(SchoolTeacher::getTeacherName,para.getTeacherName());
        }
        IPage<SchoolTeacher> list = schoolTeacherService.page(page, query);
        return ResultUtils.success("查询成功!",list);
    }

    // 教师列表
    @GetMapping("/getList")
    public ResultVo getList(){
        List<SchoolTeacher> list = schoolTeacherService.list();
        return ResultUtils.success("查询成功!",list);
    }

    // 角色列表
    @GetMapping("/getRole")
    public ResultVo getRole(){
        List<SysRole> list = sysRoleService.list();
        return ResultUtils.success("查询成功!",list);
    }

    // 角色查询
    @GetMapping("/getRoleById")
    public ResultVo getRoleById(Long teacherId){
        QueryWrapper<TeacherRole> query = new QueryWrapper<>();
        query.lambda().eq(TeacherRole::getTeacherId,teacherId);
        TeacherRole one = teacherRoleService.getOne(query);
        return ResultUtils.success("查询成功!",one);
    }
}
