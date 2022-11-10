package com.sdu.web.sys_login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdu.config.jwt.JwtUtils;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.school_class.service.SchoolClassService;
import com.sdu.web.school_major.service.SchoolMajorService;
import com.sdu.web.school_student.entity.SchoolStudent;
import com.sdu.web.school_student.service.SchoolStudentService;
import com.sdu.web.school_teacher.entity.SchoolTeacher;
import com.sdu.web.school_teacher.service.SchoolTeacherService;
import com.sdu.web.sys_login.entity.*;
import com.sdu.web.sys_menu.entity.MakeTree;
import com.sdu.web.sys_menu.entity.RouterVo;
import com.sdu.web.sys_menu.entity.SysMenu;
import com.sdu.web.sys_menu.service.SysMenuService;
import com.sdu.web.sys_user.entity.SysUser;
import com.sdu.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Connor
 * @date 2022/11/4 22:11
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SchoolStudentService schoolStudentService;
    @Autowired
    private SchoolTeacherService schoolTeacherService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private SchoolMajorService schoolMajorService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录
     * @param loginPara
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginPara loginPara) {
        if (StringUtils.isEmpty(loginPara.getUsername()) || StringUtils.isEmpty(loginPara.getPassword()) || StringUtils.isEmpty(loginPara.getUserType())) {
            return ResultUtils.error("用户名、密码或用户类型不能为空!");
        }
        String password = DigestUtils.md5DigestAsHex(loginPara.getPassword().getBytes());
        // 获取登录用户角色
        if (loginPara.getUserType().equals("0")) { // 学生
            QueryWrapper<SchoolStudent> query = new QueryWrapper<>();
            query.lambda().eq(SchoolStudent::getStuNum, loginPara.getUsername())
                    .eq(SchoolStudent::getPassword, password);
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
            query.lambda().eq(SchoolTeacher::getTeacherNum, loginPara.getUsername())
                    .eq(SchoolTeacher::getPassword, password);
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
            SysUser user = sysUserService.getOne(query);
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
            result.setUserType(loginPara.getUserType());
            result.setToken(token);
            result.setUsername(user.getUsername());
            return ResultUtils.success("登录成功!", result);
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }

    /**
     * 获取信息
     * @param loginPara
     * @return
     */
    @GetMapping("/getInfo")
    public ResultVo getInfo(LoginResult loginPara) {
        UserInfo userInfo = new UserInfo();
        if (loginPara.getUserType().equals("0")) { // 学生
            // 根据学生id查询学生信息
            SchoolStudent student = schoolStudentService.getById(loginPara.getUserId());
            userInfo.setName(student.getStuName());
            userInfo.setIntroduction(student.getStuName());
            userInfo.setAvatar("//https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            // 获取学生的权限字段
            List<SysMenu> menuList = sysMenuService.getMenuByStuId(student.getStuId());
            // 过滤权限字段
            List<String> collect = menuList.stream().filter(item -> item != null && StringUtils.isNotEmpty(item.getCode())).map(item -> item.getCode())
                    .collect(Collectors.toList());
            // 转为数组
            if (collect.size() == 0) {
                return ResultUtils.error("未分配菜单权限!");
            }
            String[] array = collect.toArray(new String[collect.size()]);
            userInfo.setRoles(array);
            return ResultUtils.success("查询成功!", userInfo);
        } else if (loginPara.getUserType().equals("1")) { // 教师
            // 根据教师id查询教师信息
            SchoolTeacher teacher = schoolTeacherService.getById(loginPara.getUserId());
            userInfo.setName(teacher.getTeacherName());
            userInfo.setIntroduction(teacher.getTeacherName());
            userInfo.setAvatar("//https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            // 获取教师的权限字段
            List<SysMenu> menuList = sysMenuService.getMenuByTeacherId(teacher.getTeacherId());
            // 过滤权限字段
            List<String> collect = menuList.stream().filter(item -> item != null && StringUtils.isNotEmpty(item.getCode())).map(item -> item.getCode())
                    .collect(Collectors.toList());
            // 转为数组
            if (collect.size() == 0) {
                return ResultUtils.error("未分配菜单权限!");
            }
            String[] array = collect.toArray(new String[collect.size()]);
            userInfo.setRoles(array);
            return ResultUtils.success("查询成功!", userInfo);
        } else if (loginPara.getUserType().equals("2")) { // 管理员
            // 根据用户id查询用户信息
            SysUser user = sysUserService.getById(loginPara.getUserId());
            userInfo.setName(user.getUsername());
            userInfo.setIntroduction(user.getNickName());
            userInfo.setAvatar("//https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            // 获取用户的权限字段
            List<SysMenu> menuList = null;
            if (StringUtils.isNotEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("1")) { // 超级管理员
                menuList = sysMenuService.list();
            } else {
                menuList = sysMenuService.getMenuByUserId(user.getUserId());
            }
            // 过滤权限字段
            List<String> collect = menuList.stream().filter(item -> item != null && StringUtils.isNotEmpty(item.getCode())).map(item -> item.getCode())
                    .collect(Collectors.toList());
            // 转为数组
            if (collect.size() == 0) {
                return ResultUtils.error("未分配菜单权限!");
            }
            String[] array = collect.toArray(new String[collect.size()]);
            userInfo.setRoles(array);
            return ResultUtils.success("查询成功!", userInfo);
        } else {
            return ResultUtils.success("用户类型不存在!");
        }
    }

    /**
     * 获取菜单
     * @param loginPara
     * @return
     */
    @GetMapping("/getMenuList")
    public ResultVo getMenuList(LoginResult loginPara) {
        if (loginPara.getUserType().equals("0")) {
            List<SysMenu> menuList = sysMenuService.getMenuByStuId(loginPara.getUserId());
            // 过滤
            List<SysMenu> collect = menuList.stream().filter(item -> item != null && !item.getType().equals("2"))
                    .collect(Collectors.toList());
            if (collect.size() == 0) {
                return ResultUtils.error("暂未分配菜单权限，请联系管理员!");
            }
            // 组装路由数据格式
            List<RouterVo> router = MakeTree.makeRouter(collect, 0L);
            return ResultUtils.success("查询成功!", router);
        } else if (loginPara.getUserType().equals("1")) {
            List<SysMenu> menuList = sysMenuService.getMenuByTeacherId(loginPara.getUserId());
            // 过滤
            List<SysMenu> collect = menuList.stream().filter(item -> item != null && !item.getType().equals("2"))
                    .collect(Collectors.toList());
            if (collect.size() == 0) {
                return ResultUtils.error("暂未分配菜单权限，请联系管理员!");
            }
            // 组装路由数据格式
            List<RouterVo> router = MakeTree.makeRouter(collect, 0L);
            return ResultUtils.success("查询成功!", router);
        } else if (loginPara.getUserType().equals("2")) {
            // 根据用户id查询用户信息
            SysUser user = sysUserService.getById(loginPara.getUserId());
            // 菜单数据获取
            List<SysMenu> menuList = null;
            if (StringUtils.isNotEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("1")) {
                menuList = sysMenuService.list();
            } else {
                menuList = sysMenuService.getMenuByUserId(user.getUserId());
            }
            // 过滤
            List<SysMenu> collect = menuList.stream().filter(item -> item != null && !item.getType().equals("2"))
                    .collect(Collectors.toList());
            if (collect.size() == 0) {
                return ResultUtils.error("暂未分配菜单权限，请联系管理员!");
            }
            // 组装路由数据格式
            List<RouterVo> router = MakeTree.makeRouter(collect, 0L);
            return ResultUtils.success("查询成功!", router);
        } else {
            return ResultUtils.success("用户类型不存在!");
        }
    }

    /**
     * 修改密码
     * @param para
     * @return
     */
    @PostMapping("/updatePassword")
    public ResultVo UpdatePassword(@RequestBody UpdatePasswordPara para) {
        String old = DigestUtils.md5DigestAsHex(para.getOldPassword().getBytes());
        if (para.getUserType().equals("0")) { // 学生
            SchoolStudent student = schoolStudentService.getById(para.getUserId());
            if (!student.getPassword().equals(old)) {
                return ResultUtils.error("原密码错误!");
            }
            SchoolStudent student1 = new SchoolStudent();
            student1.setStuId(student.getStuId());
            student1.setPassword(DigestUtils.md5DigestAsHex(para.getNewPassword().getBytes()));
            schoolStudentService.updateById(student1);
            return ResultUtils.success("密码更新成功!");
        } else if (para.getUserType().equals("1")) { // 教师
            SchoolTeacher teacher = schoolTeacherService.getById(para.getUserId());
            if (!teacher.getPassword().equals(old)) {
                return ResultUtils.error("原密码错误!");
            }
            SchoolTeacher teacher1 = new SchoolTeacher();
            teacher1.setTeacherId(teacher.getTeacherId());
            teacher1.setPassword(DigestUtils.md5DigestAsHex(para.getNewPassword().getBytes()));
            schoolTeacherService.updateById(teacher1);
            return ResultUtils.success("密码更新成功!");
        } else if (para.getUserType().equals("2")) { // 管理员
            SysUser user = sysUserService.getById(para.getUserId());
            if (!user.getPassword().equals(old)) {
                return ResultUtils.error("原密码错误!");
            }
            SysUser user1 = new SysUser();
            user1.setUserId(user.getUserId());
            user1.setPassword(DigestUtils.md5DigestAsHex(para.getNewPassword().getBytes()));
            sysUserService.updateById(user1);
            return ResultUtils.success("密码更新成功!");
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }

    /**
     * 首页总数统计
     * @return
     */
    @GetMapping("/getHomeCount")
    public ResultVo getHomeCount(){
        CountVo countVo = new CountVo();
        // 学生总数
        int stuCount = schoolStudentService.count();
        countVo.setStuCount(stuCount);
        // 班级总数
        int classCount = schoolClassService.count();
        countVo.setClassCount(classCount);
        // 专业总数
        int majorCount = schoolMajorService.count();
        countVo.setMajorCount(majorCount);
        // 教师总数
        int teacherCount = schoolTeacherService.count();
        countVo.setTeacherCount(teacherCount);
        return ResultUtils.success("查询成功!",countVo);
    }
}
