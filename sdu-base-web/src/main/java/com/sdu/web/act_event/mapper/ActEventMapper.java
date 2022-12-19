package com.sdu.web.act_event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.web.act_event.entity.ActEvent;
import com.sdu.web.act_event.entity.EventListPara;
import org.apache.ibatis.annotations.Param;

public interface ActEventMapper extends BaseMapper<ActEvent> {
    IPage<ActEvent> getEventList(IPage<ActEvent> page, @Param("para") EventListPara para);
}
