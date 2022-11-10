package com.sdu.web.sys_role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.sys_menu.entity.MakeTree;
import com.sdu.web.sys_menu.entity.SysMenu;
import com.sdu.web.sys_menu.service.SysMenuService;
import com.sdu.web.sys_role.entity.AssignPara;
import com.sdu.web.sys_role.entity.AssignVo;
import com.sdu.web.sys_role.entity.RolePara;
import com.sdu.web.sys_role.entity.SysRole;
import com.sdu.web.sys_role.mapper.SysRoleMapper;
import com.sdu.web.sys_role.service.SysRoleService;
import com.sdu.web.sys_user.entity.SysUser;
import com.sdu.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Connor
 * @date 2022/11/3 23:41
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysRole> list(RolePara para) {
        // 构造分页对象
        IPage<SysRole> page = new Page<>();
        page.setSize(para.getPageSize());
        page.setCurrent(para.getCurrentPage());
        // 构造查询条件
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(para.getRoleName())){
            query.lambda().like(SysRole::getRoleName,para.getRoleName());
        }

        return this.baseMapper.selectPage(page,query);
    }

    @Override
    public AssignVo getAssignShow(AssignPara para) {
        // 查询当前用户的信息
        SysUser user = sysUserService.getById(para.getUserId());

        // 判断用户是否是超级管理员
        List<SysMenu> list = null; // 菜单数据
        if(user.getIsAdmin().equals("1")){ // 如果是超级管理员,拥有所有的权限
            QueryWrapper<SysMenu> query = new QueryWrapper<>();
            query.lambda().orderByAsc(SysMenu::getOrderNum);
            list = sysMenuService.list(query);
        }else{ // 如果不是超级管理员,根据用户的id查询菜单
            list = sysMenuService.getMenuByUserId(user.getUserId());
        }
        // 组装菜单树
        List<SysMenu> menuList = MakeTree.makeMenuTree(list, 0L);
        // 查询角色原来的菜单
        List<SysMenu> roleList = sysMenuService.getMenuByRoleId(para.getRoleId());
        // 过滤出菜单id
        List<Long> ids = new ArrayList<>();
        Optional.ofNullable(roleList).orElse(new ArrayList<>()) // 如果为空,则直接返回一个空ArrayList
                .stream()
                .filter(item -> item != null) // 过滤出里面不为null的项
                .forEach(item -> {
                    ids.add(item.getMenuId()); // 把过滤出来的项放进ids
                });
        // 组装返回值数据
        AssignVo vo = new AssignVo();
        vo.setMenuList(menuList);
        vo.setCheckList(ids.toArray());
        return vo;
    }
}
