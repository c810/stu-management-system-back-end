package com.sdu.web.sys_role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.sys_role.entity.*;
import com.sdu.web.sys_role.service.SysRoleService;
import com.sdu.web.sys_role_menu.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Connor
 * @date 2022/11/3 23:44
 */
@RestController
@RequestMapping("/api/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    // 新增角色
    @PostMapping
    public ResultVo addRole(@RequestBody SysRole role) {
        // 设置时间
        role.setCreateTime(new Date());
        boolean save = sysRoleService.save(role);
        if(save){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    // 编辑角色
    @PutMapping
    public ResultVo editRole(@RequestBody SysRole role) {
        // 更新时间
        role.setUpdateTime(new Date());
        boolean save = sysRoleService.updateById(role);
        if(save){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    // 删除角色
    @DeleteMapping("/{roleId}")
    public ResultVo deleteRole(@PathVariable("roleId") Long roleId){
        boolean remove = sysRoleService.removeById(roleId);
        if(remove){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    // 角色列表
    @GetMapping("/list")
    public ResultVo getList(RolePara para){
        IPage<SysRole> list = sysRoleService.list(para);
        return ResultUtils.success("查询成功!", list);
    }

    // 分配权限数据回显(查询角色权限树的回显)
    @GetMapping("/getAssignShow")
    public ResultVo getAssignShow(AssignPara para) {
        AssignVo show = sysRoleService.getAssignShow(para);
        return ResultUtils.success("查询成功!", show);
    }

    // 分配权限保存
    @PostMapping("/saveRoleMenu")
    public ResultVo saveRoleMenu(@RequestBody SaveRolePara para){
        sysRoleMenuService.saveRoleMenu(para.getRoleId(),para.getList());
        return ResultUtils.success("分配权限成功!");
    }
}
