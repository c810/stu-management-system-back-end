package com.sdu.web.school_major.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.school_college.entity.SchoolCollege;
import com.sdu.web.school_college.service.SchoolCollegeService;
import com.sdu.web.school_major.entity.MajorList;
import com.sdu.web.school_major.entity.SchoolMajor;
import com.sdu.web.school_major.service.SchoolMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/5 22:28
 */
@RestController
@RequestMapping("/api/major")
public class SchoolMajorController {
    @Autowired
    private SchoolMajorService schoolMajorService;
    @Autowired
    private SchoolCollegeService schoolCollegeService;
    // 新增
    @PostMapping
    public ResultVo add(@RequestBody SchoolMajor schoolMajor){
        boolean save = schoolMajorService.save(schoolMajor);
        if(save){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody SchoolMajor schoolMajor){
        boolean save = schoolMajorService.updateById(schoolMajor);
        if(save){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    // 删除
    @DeleteMapping("/{majorId}")
    public ResultVo delete(@PathVariable("majorId") Long majorId){
        boolean save = schoolMajorService.removeById(majorId);
        if(save){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    // 列表查询
    @GetMapping("/list")
    public ResultVo getList(MajorList majorList){
        IPage<SchoolMajor> list = schoolMajorService.getList(majorList);
        return ResultUtils.success("查询成功!",list);
    }

    // 查询学院列表
    @GetMapping("/college")
    public ResultVo getCollegeList(){
        List<SchoolCollege> list = schoolCollegeService.list();
        return ResultUtils.success("查询成功!",list);
    }
}
