package com.sdu.web.reset_password.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdu.web.reset_password.entity.ResetPassword;

/**
 * @author Connor
 * @date 2022/11/12 1:29
 * @description 定义重置密码的接口类
 */
public interface ResetPasswordService extends IService<ResetPassword> {
    /**
     * 通过userId判断resetPassword表中该用户是否修改过密码，有没有数据
     * @param userId 用户id
     * @return boolean
     */
    boolean isExistUserId(Long userId);

    /**
     * 通过userId取出这一条数据
     * @param userId 用户id
     * @return resetPassword对象
     */
    ResetPassword getOneByUserId(Long userId);
}
