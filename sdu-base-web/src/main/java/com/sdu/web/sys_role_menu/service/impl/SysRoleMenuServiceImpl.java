package com.sdu.web.sys_role_menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.sys_role_menu.entity.SysRoleMenu;
import com.sdu.web.sys_role_menu.mapper.SysRoleMenuMapper;
import com.sdu.web.sys_role_menu.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/5 0:49
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    @Transactional
    public void saveRoleMenu(Long roleId, List<Long> menuIds) {
        // 先删除原来的，再插入
        QueryWrapper<SysRoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(SysRoleMenu::getRoleId,roleId);
        this.baseMapper.delete(query);
        // 插入新的
        this.baseMapper.saveRoleMenu(roleId,menuIds);
    }
}
