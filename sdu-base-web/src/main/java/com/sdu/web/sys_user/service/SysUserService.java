package com.sdu.web.sys_user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.sys_user.entity.PagePara;
import com.sdu.web.sys_user.entity.SysUser;

/**
 * @author Connor
 * @date 2022/11/3 16:48
 */
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> list(PagePara para);
}
