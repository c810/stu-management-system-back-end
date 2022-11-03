package com.sdu.web.sys_user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.sys_user.entity.PagePara;
import com.sdu.web.sys_user.entity.SysUser;
import com.sdu.web.sys_user.mapper.SysUserMapper;
import com.sdu.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Connor
 * @date 2022/11/3 16:50
 */
// 继承MyBatisPlus的ServiceImpl实现类,有常用的增删改查方法
// 实现自己的SysUserService接口,可以实现自己的增删改查接口中的方法
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
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
}
