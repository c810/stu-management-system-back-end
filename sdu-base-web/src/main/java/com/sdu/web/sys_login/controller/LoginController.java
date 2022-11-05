package com.sdu.web.sys_login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdu.config.jwt.JwtUtils;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.sys_login.entity.LoginPara;
import com.sdu.web.sys_login.entity.LoginResult;
import com.sdu.web.sys_user.entity.SysUser;
import com.sdu.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Connor
 * @date 2022/11/4 22:11
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginPara loginPara) {
        if (StringUtils.isEmpty(loginPara.getUsername()) || StringUtils.isEmpty(loginPara.getPassword()) || StringUtils.isEmpty(loginPara.getUserType())) {
            return ResultUtils.error("用户名、密码或用户类型不能为空!");
        }
        String password = DigestUtils.md5DigestAsHex(loginPara.getPassword().getBytes());
        // 查询用户是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, loginPara.getUsername()).eq(SysUser::getPassword, password);
        SysUser user = userService.getOne(query);
        if (user == null) {
            return ResultUtils.error("用户名或密码错误!");
        }
        // 生成token
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("userId", Long.toString(user.getUserId()));
        String token = jwtUtils.generateToken(map);
        // 定义返回值
        LoginResult result = new LoginResult();
        result.setUserId(user.getUserId());
        result.setToken(token);
        result.setUsername(user.getUsername());
        return ResultUtils.success("登录成功!", result);
    }
}
