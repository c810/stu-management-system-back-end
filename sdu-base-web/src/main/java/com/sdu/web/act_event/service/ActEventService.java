package com.sdu.web.act_event.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.act_event.entity.ActEvent;
import com.sdu.web.act_event.entity.EventListPara;
import com.sdu.web.act_practice.entity.ActPractice;


public interface ActEventService extends IService<ActEvent> {
    IPage<ActEvent> getEventList(EventListPara para);
}
