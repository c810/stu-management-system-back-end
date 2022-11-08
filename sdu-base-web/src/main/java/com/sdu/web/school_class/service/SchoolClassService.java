package com.sdu.web.school_class.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.school_class.entity.SchoolClass;
import com.sdu.web.school_class.entity.SchoolClassPara;

/**
 * @author Connor
 * @date 2022/11/6 1:27
 */
public interface SchoolClassService extends IService<SchoolClass> {
    IPage<SchoolClass> getList(SchoolClassPara para);
}
