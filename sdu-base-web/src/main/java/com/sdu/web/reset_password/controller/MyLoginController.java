package com.sdu.web.reset_password.controller;

import com.sdu.utils.result.Result;
import com.sdu.utils.result.ResultFactory;
import com.sdu.web.sys_user.entity.SysUser;
import com.sdu.web.sys_user.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Connor
 * @date 2022/11/12 2:16
 * @description 后端登录控制器，处理前端请求
 */
@RestController
@CrossOrigin(value = "http://localhost:8089", maxAge = 1800, allowedHeaders ="Content-Type")
public class MyLoginController {

    @Autowired
    private SysUserServiceImpl sysUserServiceImpl;

    @PostMapping("/api/resetPassword")
    public Result resetPassword(@RequestBody SysUser user) {
        if (user.getUsername()!=null&& user.getEmail()!=null){
            //第一步，传来的是用户名和邮件，其他为空，则生成验证码并发送邮件
            return sysUserServiceImpl.checkUserAndEmail(user);
        }
        if (user.getCode()!=null && user.getUsername()!=null) {
            //第二步，传来code和username，需要验证数据库中的code是否正确
            return sysUserServiceImpl.checkCode(user.getCode(), user.getUsername());
        }
        if (user.getUsername()!=null && user.getPassword()!=null) {
            //最后，用户名和密码同时传过来，开始重置密码。
            return sysUserServiceImpl.resetPassword(user.getUsername(), user.getPassword());
        }
        return ResultFactory.buildFailResult("未知错误");
    }

}
