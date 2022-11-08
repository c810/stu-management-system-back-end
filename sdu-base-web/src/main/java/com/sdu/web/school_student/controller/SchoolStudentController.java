package com.sdu.web.school_student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.school_class.entity.SchoolClass;
import com.sdu.web.school_class.service.SchoolClassService;
import com.sdu.web.school_student.entity.SchoolStudent;
import com.sdu.web.school_student.entity.StuPara;
import com.sdu.web.school_student.service.SchoolStudentService;
import com.sdu.web.stu_role.entity.StuRole;
import com.sdu.web.stu_role.service.StuRoleService;
import com.sdu.web.sys_role.entity.SysRole;
import com.sdu.web.sys_role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/6 12:51
 */
@RestController
@RequestMapping("/api/student")
public class SchoolStudentController {
    @Autowired
    private SchoolStudentService schoolStudentService;
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private StuRoleService stuRoleService;

    // 新增
    @PostMapping
    public ResultVo add(@RequestBody SchoolStudent schoolStudent){
        schoolStudent.setPassword(DigestUtils.md5DigestAsHex(schoolStudent.getPassword().getBytes()));
        schoolStudentService.addStu(schoolStudent);
        return ResultUtils.success("新增成功!");
    }

    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody SchoolStudent schoolStudent){
        schoolStudentService.editStu(schoolStudent);
        return ResultUtils.success("编辑成功!");
    }


    // 删除
    @DeleteMapping("/{stuId}")
    public ResultVo delete(@PathVariable("stuId") Long stuId){
        schoolStudentService.deleteStu(stuId);
        return ResultUtils.success("删除成功!");
    }

    // 根据学生id查询学生
    @GetMapping("/getById")
    public ResultVo getById(Long stuId){
        SchoolStudent stu = schoolStudentService.getStuById(stuId);
        return ResultUtils.success("查询成功!",stu);
    }

    // 查询列表
    @GetMapping("/list")
    public ResultVo getList(StuPara para){
        IPage<SchoolStudent> list = schoolStudentService.getList(para);
        return ResultUtils.success("查询成功!",list);
    }

    // 根据专业id查询班级列表
    @GetMapping("/getClassList")
    public ResultVo getClassList(Long majorId){
        QueryWrapper<SchoolClass> query = new QueryWrapper<>();
        query.lambda().eq(SchoolClass::getMajorId,majorId);
        List<SchoolClass> list = schoolClassService.list(query);
        return ResultUtils.success("查询成功!",list);
    }

    // 查询学生角色
    @GetMapping("/getRoleList")
    public ResultVo getRoleList(){
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.lambda().eq(SysRole::getRoleType,"2");
        List<SysRole> list = sysRoleService.list(query);
        return ResultUtils.success("查询成功!",list);
    }

    // 根据学生id查询角色id
    @GetMapping("/getRoleId")
    public ResultVo getRoleId(Long stuId){
        QueryWrapper<StuRole> query = new QueryWrapper<>();
        query.lambda().eq(StuRole::getStuId,stuId);
        StuRole role = stuRoleService.getOne(query);
        return ResultUtils.success("查询成功!",role);
    }


}

