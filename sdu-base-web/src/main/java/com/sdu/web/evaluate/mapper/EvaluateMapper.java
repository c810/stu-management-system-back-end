package com.sdu.web.evaluate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.web.evaluate.entity.Evaluate;
import com.sdu.web.evaluate.entity.EvaluatePara;
import com.sdu.web.evaluate.entity.EvaluateVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author Connor
 * @date 2022/11/13 22:48
 * @description
 */
public interface EvaluateMapper extends BaseMapper<Evaluate> {
    // 查询学生的教师
    IPage<EvaluateVo> getTeacherList(IPage<EvaluateVo> page, @Param("para") EvaluatePara para);

    IPage<Evaluate> getMyEvaluateList(IPage<Evaluate> page, @Param("para") EvaluatePara para);
}
