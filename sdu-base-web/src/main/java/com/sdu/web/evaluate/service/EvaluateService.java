package com.sdu.web.evaluate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.evaluate.entity.Evaluate;
import com.sdu.web.evaluate.entity.EvaluatePara;
import com.sdu.web.evaluate.entity.EvaluateVo;

/**
 * @author Connor
 * @date 2022/11/13 22:50
 * @description
 */
public interface EvaluateService extends IService<Evaluate> {
    // 查询学生的教师
    IPage<EvaluateVo> getTeacherList(EvaluatePara para);
    IPage<Evaluate> getMyEvaluateList(EvaluatePara para);
}
