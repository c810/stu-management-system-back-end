package com.sdu.web.school_class.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.school_class.entity.SchoolClass;
import com.sdu.web.school_class.entity.SchoolClassPara;
import com.sdu.web.school_class.service.SchoolClassService;
import com.sdu.web.school_major.entity.SchoolMajor;
import com.sdu.web.school_major.service.SchoolMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/6 1:28
 */
@RestController
@RequestMapping("/api/class")
public class SchoolClassController {
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private SchoolMajorService schoolMajorService;

    // 新增
    @PostMapping
    public ResultVo add(@RequestBody SchoolClass schoolClass) {
        boolean save = schoolClassService.save(schoolClass);
        if (save) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody SchoolClass schoolClass) {
        boolean save = schoolClassService.updateById(schoolClass);
        if (save) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    // 删除
    @DeleteMapping("/{classId}")
    public ResultVo delete(@PathVariable("classId") Long classId) {
        boolean save = schoolClassService.removeById(classId);
        if (save) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    // 查询列表
    @GetMapping("/list")
    public ResultVo getList(SchoolClassPara schoolClassPara) {
        IPage<SchoolClass> list = schoolClassService.getList(schoolClassPara);
        return ResultUtils.success("查询成功!", list);
    }

    // 根据学院id查询专业
    @GetMapping("/getMajorListByCollegeId")
    public ResultVo getMajorListByCollegeId(Long collegeId) {
        // 构造查询条件
        QueryWrapper<SchoolMajor> query = new QueryWrapper<>();
        query.lambda().eq(SchoolMajor::getCollegeId, collegeId);
        List<SchoolMajor> list = schoolMajorService.list(query);
        return ResultUtils.success("查询成功!", list);
    }

    // 根据专业id查询学院id
    @GetMapping("/getCollegeId")
    public ResultVo getCollegeId(Long majorId) {
        SchoolMajor major = schoolMajorService.getById(majorId);
        return ResultUtils.success("查询成功!",major);
    }
}
