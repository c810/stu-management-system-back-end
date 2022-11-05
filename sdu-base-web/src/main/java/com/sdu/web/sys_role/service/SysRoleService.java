package com.sdu.web.sys_role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.sys_role.entity.AssignPara;
import com.sdu.web.sys_role.entity.AssignVo;
import com.sdu.web.sys_role.entity.RolePara;
import com.sdu.web.sys_role.entity.SysRole;

/**
 * @author Connor
 * @date 2022/11/3 23:40
 */
public interface SysRoleService extends IService<SysRole>  {
    IPage<SysRole> list(RolePara para);
    // 角色权限的回显
    AssignVo getAssignShow(AssignPara para);
}
