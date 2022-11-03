package com.sdu.web.sys_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdu.web.sys_user.entity.SysUser;

/**
 * @author Connor
 * @date 2022/11/2 22:09
 */
// BaseMapper是MyBatisPlus提供的接口方法(包括了一些增删改查的方法)
// 注意别忘了配置SysUserMapper.xml文件
public interface SysUserMapper extends BaseMapper<SysUser> {
}
