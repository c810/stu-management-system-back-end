package com.sdu.web.evaluate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.evaluate.entity.Evaluate;
import com.sdu.web.evaluate.entity.EvaluatePara;
import com.sdu.web.evaluate.entity.EvaluateVo;
import com.sdu.web.evaluate.mapper.EvaluateMapper;
import com.sdu.web.evaluate.service.EvaluateService;
import org.springframework.stereotype.Service;

/**
 * @author Connor
 * @date 2022/11/13 22:50
 * @description
 */
@Service
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements EvaluateService {
    @Override
    public IPage<EvaluateVo> getTeacherList(EvaluatePara para) {
        // 构造分页对象
        IPage<EvaluateVo> page = new Page<>(para.getCurrentPage(),para.getPageSize());
        return this.baseMapper.getTeacherList(page,para);
    }
    @Override
    public IPage<Evaluate> getMyEvaluateList(EvaluatePara para) {
        // 构造分页对象
        IPage<Evaluate> page = new Page<>(para.getCurrentPage(), para.getPageSize());
        return this.baseMapper.getMyEvaluateList(page, para);
    }
}
