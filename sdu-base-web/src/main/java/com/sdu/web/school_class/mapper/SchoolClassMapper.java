package com.sdu.web.school_class.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.web.school_class.entity.SchoolClass;
import com.sdu.web.school_class.entity.SchoolClassPara;
import org.apache.ibatis.annotations.Param;

/**
 * @author Connor
 * @date 2022/11/6 1:23
 */
public interface SchoolClassMapper extends BaseMapper<SchoolClass> {
    IPage<SchoolClass> getList(IPage<SchoolClass> page, @Param("para") SchoolClassPara para);
}
