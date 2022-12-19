package com.sdu.web.act_practice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.act_practice.entity.ActPractice;
import com.sdu.web.act_practice.entity.PracticeListPara;

public interface ActPracticeService extends IService<ActPractice> {
    IPage<ActPractice> getPracticeList(PracticeListPara para);

}
