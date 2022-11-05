package com.sdu.web.sys_role_menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.sys_role_menu.entity.SysRoleMenu;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/5 0:48
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    void saveRoleMenu(Long roleId, List<Long> menuIds);
}
