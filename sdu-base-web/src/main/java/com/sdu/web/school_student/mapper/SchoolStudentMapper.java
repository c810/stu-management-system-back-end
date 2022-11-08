package com.sdu.web.school_student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.web.school_student.entity.SchoolStudent;
import com.sdu.web.school_student.entity.StuPara;
import org.apache.ibatis.annotations.Param;

/**
 * @author Connor
 * @date 2022/11/6 12:39
 */
public interface SchoolStudentMapper extends BaseMapper<SchoolStudent> {
    //根据用户id查询用户信息
    SchoolStudent getStuById(@Param("stuId") Long stuId);
    //列表
    IPage<SchoolStudent> getList(IPage<SchoolStudent> page, @Param("para") StuPara para);
}
