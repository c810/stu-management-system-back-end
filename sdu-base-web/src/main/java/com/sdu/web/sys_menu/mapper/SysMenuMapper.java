package com.sdu.web.sys_menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdu.web.sys_menu.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Connor
 * @date 2022/11/4 11:35
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    // 根据用户id查询权限
    List<SysMenu> getMenuByUserId(@Param("userId") Long userId);
    // 根据角色id查询权限
    List<SysMenu> getMenuByRoleId(@Param("roleId") Long roleId);
    // 根据学生id查询权限
    List<SysMenu> getMenuByStuId(@Param("userId") Long userId);
    // 根据教师id查询权限
    List<SysMenu> getMenuByTeacherId(@Param("userId") Long userId);
}
