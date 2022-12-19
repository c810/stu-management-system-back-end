package com.sdu.web.act_event.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.act_event.entity.ActEvent;
import com.sdu.web.act_event.entity.EventListPara;
import com.sdu.web.act_event.mapper.ActEventMapper;
import com.sdu.web.act_event.service.ActEventService;
import org.springframework.stereotype.Service;

@Service
public class ActEventServiceImpl extends ServiceImpl<ActEventMapper, ActEvent> implements ActEventService {
    @Override
    public IPage<ActEvent> getEventList(EventListPara para) {
        // 构造分页对象
        IPage<ActEvent> page = new Page<>(para.getCurrentPage(),para.getPageSize());
        return this.baseMapper.getEventList(page,para);
    }
}
