package com.sdu.web.sys_user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.sys_user.entity.PagePara;
import com.sdu.web.sys_user.entity.SysUser;
import com.sdu.web.sys_user.mapper.SysUserMapper;
import com.sdu.web.sys_user.service.SysUserService;
import com.sdu.web.sys_user_role.entity.SysUserRole;
import com.sdu.web.sys_user_role.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Connor
 * @date 2022/11/3 16:50
 */
// 继承MyBatisPlus的ServiceImpl实现类,有常用的增删改查方法
// 实现自己的SysUserService接口,可以实现自己的增删改查接口中的方法
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Override
    public IPage<SysUser> list(PagePara para) {
        // 构造分页对象
        IPage<SysUser> page = new Page<>();
        page.setCurrent(para.getCurrentPage());
        page.setSize(para.getPageSize());
        // 构造查询条件
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(para.getNickName())){
            query.lambda().like(SysUser::getNickName,para.getNickName());
        }
        if(StringUtils.isNotEmpty(para.getPhone())){
            query.lambda().like(SysUser::getPhone,para.getPhone());
        }
        return this.baseMapper.selectPage(page,query);
    }

    @Override
    @Transactional // 管理事务,保证同时成功或失败
    public void add(SysUser user) {
        // 保存用户
        int insert = this.baseMapper.insert(user);
        // 保存角色
        if(insert > 0){
            // 角色保存
            SysUserRole role = new SysUserRole();
            role.setUserId(user.getUserId());
            role.setRoleId(user.getRoleId());
            sysUserRoleService.save(role);
        }
    }

    @Override
    @Transactional
    public void edit(SysUser user) {
        // 编辑用户
        int i = this.baseMapper.updateById(user);
        // 角色:先删除,重新插入
        if(i > 0){
            // 先删除
            QueryWrapper<SysUserRole> query = new QueryWrapper<>();
            query.lambda().eq(SysUserRole::getUserId,user.getUserId());
            sysUserRoleService.remove(query);
            // 重新插入
            SysUserRole role = new SysUserRole();
            role.setUserId(user.getUserId());
            role.setRoleId(user.getRoleId());
            sysUserRoleService.save(role);
        }
    }
}
