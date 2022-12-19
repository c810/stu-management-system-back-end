package com.sdu.web.act_practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.web.act_practice.entity.ActPractice;
import com.sdu.web.act_practice.entity.PracticeListPara;
import org.apache.ibatis.annotations.Param;

public interface ActPracticeMapper extends BaseMapper<ActPractice> {
    IPage<ActPractice> getPracticeList(IPage<ActPractice> page, @Param("para") PracticeListPara para);

}
