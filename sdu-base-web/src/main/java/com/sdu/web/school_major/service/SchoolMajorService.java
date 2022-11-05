package com.sdu.web.school_major.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.school_major.entity.MajorList;
import com.sdu.web.school_major.entity.SchoolMajor;

/**
 * @author Connor
 * @date 2022/11/5 22:26
 */
public interface SchoolMajorService extends IService<SchoolMajor> {
    IPage<SchoolMajor> getList(MajorList majorList);
}
