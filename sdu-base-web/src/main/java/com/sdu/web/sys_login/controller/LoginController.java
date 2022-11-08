package com.sdu.web.sys_login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdu.config.jwt.JwtUtils;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.school_student.entity.SchoolStudent;
import com.sdu.web.school_student.service.SchoolStudentService;
import com.sdu.web.school_teacher.entity.SchoolTeacher;
import com.sdu.web.school_teacher.service.SchoolTeacherService;
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
    private SchoolStudentService schoolStudentService;
    @Autowired
    private SchoolTeacherService schoolTeacherService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginPara loginPara) {
        if (StringUtils.isEmpty(loginPara.getUsername()) || StringUtils.isEmpty(loginPara.getPassword()) || StringUtils.isEmpty(loginPara.getUserType())) {
            return ResultUtils.error("用户名、密码或用户类型不能为空!");
        }
        String password = DigestUtils.md5DigestAsHex(loginPara.getPassword().getBytes());
        // 获取登录用户角色
        if (loginPara.getUserType().equals("0")) { // 学生
            QueryWrapper<SchoolStudent> query = new QueryWrapper<>();
            query.lambda().eq(SchoolStudent::getStuNum,loginPara.getUsername())
                    .eq(SchoolStudent::getPassword,password);
            SchoolStudent student = schoolStudentService.getOne(query);
            if (student == null) {
                return ResultUtils.error("用户名或密码错误!");
            }
            // 生成token
            Map<String, String> map = new HashMap<>();
            map.put("username", student.getStuNum());
            map.put("userId", Long.toString(student.getStuId()));
            String token = jwtUtils.generateToken(map);
            // 定义返回值
            LoginResult result = new LoginResult();
            result.setUserId(student.getStuId());
            result.setToken(token);
            result.setUserType(loginPara.getUserType());
            result.setUsername(student.getStuName());
            return ResultUtils.success("登录成功!", result);
        } else if (loginPara.getUserType().equals("1")) { // 教师
            QueryWrapper<SchoolTeacher> query = new QueryWrapper<>();
            query.lambda().eq(SchoolTeacher::getTeacherNum,loginPara.getUsername())
                    .eq(SchoolTeacher::getPassword,password);
            SchoolTeacher teacher = schoolTeacherService.getOne(query);
            if (teacher == null) {
                return ResultUtils.error("用户名或密码错误!");
            }
            // 生成token
            Map<String, String> map = new HashMap<>();
            map.put("username", teacher.getTeacherNum());
            map.put("userId", Long.toString(teacher.getTeacherId()));
            String token = jwtUtils.generateToken(map);
            // 定义返回值
            LoginResult result = new LoginResult();
            result.setUserId(teacher.getTeacherId());
            result.setToken(token);
            result.setUserType(loginPara.getUserType());
            result.setUsername(teacher.getTeacherName());
            return ResultUtils.success("登录成功!", result);
        } else if (loginPara.getUserType().equals("2")) { // 管理员
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
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }
}
