package com.sdu.web.sys_menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.sys_menu.entity.SysMenu;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/4 11:37
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> getList();
    List<SysMenu> getParentList();
    // 根据用户id查询权限
    List<SysMenu> getMenuByUserId(Long userId);
    // 根据角色id查询权限
    List<SysMenu> getMenuByRoleId(Long roleId);
    //根据学生id查询权限
    List<SysMenu> getMenuByStuId(Long userId);
    //根据教师id查询权限
    List<SysMenu> getMenuByTeacherId(Long userId);
}
