package com.sdu.web.sys_role.entity;

import com.sdu.web.sys_menu.entity.SysMenu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Connor
 * @date 2022/11/4 23:25
 * 封装返回值
 */
@Data
public class AssignVo {
    // 当前用户拥有的菜单
    private List<SysMenu> menuList = new ArrayList<>();
    // 被分配的角色拥有的菜单id
    private Object[] checkList;
}
