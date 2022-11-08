package com.sdu.web.school_class.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.school_class.entity.SchoolClass;
import com.sdu.web.school_class.entity.SchoolClassPara;
import com.sdu.web.school_class.mapper.SchoolClassMapper;
import com.sdu.web.school_class.service.SchoolClassService;
import org.springframework.stereotype.Service;

/**
 * @author Connor
 * @date 2022/11/6 1:27
 */
@Service
public class SchoolClassServiceImpl extends ServiceImpl<SchoolClassMapper, SchoolClass> implements SchoolClassService {
    @Override
    public IPage<SchoolClass> getList(SchoolClassPara para) {
        // 构造分页对象
        IPage<SchoolClass> page = new Page<>(para.getCurrentPage(),para.getPageSize());
        return this.baseMapper.getList(page,para);
    }
}

