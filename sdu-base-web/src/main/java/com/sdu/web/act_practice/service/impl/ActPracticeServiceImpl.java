package com.sdu.web.act_practice.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.act_practice.entity.ActPractice;
import com.sdu.web.act_practice.entity.PracticeListPara;
import com.sdu.web.act_practice.mapper.ActPracticeMapper;
import com.sdu.web.act_practice.service.ActPracticeService;
import org.springframework.stereotype.Service;

@Service
public class ActPracticeServiceImpl extends ServiceImpl<ActPracticeMapper, ActPractice> implements ActPracticeService {
    @Override
    public IPage<ActPractice> getPracticeList(PracticeListPara para) {
        // 构造分页对象
        IPage<ActPractice> page = new Page<>(para.getCurrentPage(),para.getPageSize());
        return this.baseMapper.getPracticeList(page,para);
    }

}
