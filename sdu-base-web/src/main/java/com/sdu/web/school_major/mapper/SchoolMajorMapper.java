package com.sdu.web.school_major.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.web.school_major.entity.MajorList;
import com.sdu.web.school_major.entity.SchoolMajor;
import org.apache.ibatis.annotations.Param;

/**
 * @author Connor
 * @date 2022/11/5 22:22
 */
public interface SchoolMajorMapper extends BaseMapper<SchoolMajor> {
    IPage<SchoolMajor> getList(IPage<SchoolMajor> page, @Param("para") MajorList majorList);
}
