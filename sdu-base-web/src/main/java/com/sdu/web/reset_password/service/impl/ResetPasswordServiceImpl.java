package com.sdu.web.reset_password.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.reset_password.entity.ResetPassword;
import com.sdu.web.reset_password.mapper.ResetPasswordMapper;
import com.sdu.web.reset_password.service.ResetPasswordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Connor
 * @date 2022/11/12 1:31
 * @description
 */
@Service
public class ResetPasswordServiceImpl extends ServiceImpl<ResetPasswordMapper, ResetPassword> implements ResetPasswordService {
    @Resource
    private ResetPasswordService resetPasswordService;

    /**
     * 判断是否存在该用户修改的数据，
     * @param userId 用户id
     * @return boolean
     */
    @Override
    public boolean isExistUserId(Long userId) {
        QueryWrapper<ResetPassword> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_Id", userId);
        return resetPasswordService.count(queryWrapper) > 0;
    }

    /**
     * 通过用户id获取这条数据
     * @param userId 用户id
     * @return ResetPassword实例
     */
    @Override
    public ResetPassword getOneByUserId(Long userId) {
        QueryWrapper<ResetPassword> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_Id", userId);
        return resetPasswordService.getOne(queryWrapper);
    }

}
