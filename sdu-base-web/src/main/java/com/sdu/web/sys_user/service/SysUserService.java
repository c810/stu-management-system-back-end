package com.sdu.web.sys_user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.utils.result.Result;
import com.sdu.web.sys_user.entity.PagePara;
import com.sdu.web.sys_user.entity.SysUser;

/**
 * @author Connor
 * @date 2022/11/3 16:48
 */
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> list(PagePara para);
    // 新增
    void add(SysUser user);
    // 编辑
    void edit(SysUser user);

    Result checkUserAndEmail(SysUser sysUser);

    Result checkCode(String code, String username);

    Result resetPassword(String username, String password);

    /**
     * 数据库中是否存在用户名
     * @param username 用户名
     * @return boolean
     */
    boolean isExistUser(String username);

    /**
     * 通过用户名查找对应的邮箱号
     * @param username 用户名
     * @return email 邮箱号
     */
    String findEmailByUsername(String username);

    /**
     * 通过用户名查找对应的用户id
     * @param username 用户名
     * @return id
     */
    Long findIdByUsername(String username);
}
