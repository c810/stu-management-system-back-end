package com.sdu.web.sys_user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.utils.result.Result;
import com.sdu.utils.result.ResultFactory;
import com.sdu.web.email.service.EmailService;
import com.sdu.web.reset_password.entity.ResetPassword;
import com.sdu.web.reset_password.service.ResetPasswordService;
import com.sdu.web.sys_user.entity.PagePara;
import com.sdu.web.sys_user.entity.SysUser;
import com.sdu.web.sys_user.mapper.SysUserMapper;
import com.sdu.web.sys_user.service.SysUserService;
import com.sdu.web.sys_user_role.entity.SysUserRole;
import com.sdu.web.sys_user_role.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Connor
 * @date 2022/11/3 16:50
 */
// 继承MyBatisPlus的ServiceImpl实现类,有常用的增删改查方法
// 实现自己的SysUserService接口,可以实现自己的增删改查接口中的方法
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
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

    @Override
    @Transactional // 管理事务,保证同时成功或失败
    public void add(SysUser user) {
        // 保存用户
        int insert = this.baseMapper.insert(user);
        // 保存角色
        if(insert > 0){
            // 角色保存
            SysUserRole role = new SysUserRole();
            role.setUserId(user.getUserId());
            role.setRoleId(user.getRoleId());
            sysUserRoleService.save(role);
        }
    }

    @Override
    @Transactional
    public void edit(SysUser user) {
        // 编辑用户
        int i = this.baseMapper.updateById(user);
        // 角色:先删除,重新插入
        if(i > 0){
            // 先删除
            QueryWrapper<SysUserRole> query = new QueryWrapper<>();
            query.lambda().eq(SysUserRole::getUserId,user.getUserId());
            sysUserRoleService.remove(query);
            // 重新插入
            SysUserRole role = new SysUserRole();
            role.setUserId(user.getUserId());
            role.setRoleId(user.getRoleId());
            sysUserRoleService.save(role);
        }
    }

    @Resource
    private SysUserService sysUserService;

    @Resource
    private ResetPasswordService resetPasswordService;

    @Resource
    private EmailService emailService;

    /**
     * 检查想重置密码的用户和邮箱是否匹配，若匹配成功，则返回当前用户的相关信息
     * @param sysUser 用户实体
     * @return Result信息
     */
    @Override
    public Result checkUserAndEmail(SysUser sysUser) {
        // 获取前端传来的数据
        String username = sysUser.getUsername();
        String email = sysUser.getEmail();
        // 将用户名中可能存在HTML编码转义
        username = HtmlUtils.htmlEscape(username);
        email = HtmlUtils.htmlEscape(email);
        if (isExistUser(username)) {
            // 如果存在该用户，则查找该用户的邮箱及用户id
            String storeEmail = findEmailByUsername(username);
            Long userId = findIdByUsername(username);
            if (email.equals(storeEmail)) {
                // 用户名和邮箱匹配正确后，需要在表resetPassword中生成一条信息，保存验证码，修改次数等信息
                // 生成一个6位的数字
                int num = (int) ((Math.random() * 9 + 1) * 100000);
                String code = String.valueOf(num);
                // 当前时间作为修改密码的开始时间
                Date currentTime = new Date();
                // 截至时间为开始时间延后5分钟
                Date deadline = new Date(currentTime.getTime() + 5*60*1000);
                int limitNum = 3;
                int isEffective = 1;
                if (resetPasswordService.isExistUserId(userId)) {
                    //如果表中存在这一个用户的修改信息，那么只需要修改相关信息
                    ResetPassword rp = resetPasswordService.getOneByUserId(userId);
                    if (currentTime.after(new Date(rp.getCreateTime().getTime() + 24*60*60*1000))){
                        // 如果当前的时间在修改密码存储时间后一天，那么就判断过了冷却时间，该用户可以重新设置密码, 24小时60分钟60s
                        rp.setIsEffective(isEffective);
                        rp.setResetNum(0);
                    }
                    if (rp.getResetNum() > rp.getLimitNum()) {
                        // 如果当前修改次数超过上限
                        rp.setIsEffective(0);
                    }
                    // 先判断用户是否能够修改密码, 值为1代表允许，否则不能修改
                    if (rp.getIsEffective() == 1) {
                        // 设置对应的数据
                        rp.setCode(code);
                        rp.setCreateTime(currentTime);
                        rp.setDeadline(deadline);
                        // 重置次数+1
                        int resetTimes = rp.getResetNum();
                        resetTimes++;
                        rp.setResetNum(resetTimes);
                        rp.setLimitNum(limitNum);
                        if (rp.getResetNum() > rp.getLimitNum()) {
                            // 修改次数 > 限制次数
                            rp.setIsEffective(0);
                        }else {
                            rp.setIsEffective(isEffective);
                        }
                    }else {
                        return ResultFactory.buildFailResult("当日账号密码修改次数超过上限，请明天重试！");
                    }
                    try{
                        resetPasswordService.updateById(rp);
                    }catch (Exception e) {
                        System.out.println(e);
                    }
                }else {
                    // 表中不存在这个用户，那么需要重新添加一条新数据
                    ResetPassword rp = new ResetPassword();
                    rp.setUserId(userId);
                    rp.setCode(code);
                    rp.setCreateTime(currentTime);
                    rp.setDeadline(deadline);
                    rp.setIsEffective(isEffective);
                    rp.setResetNum(0);
                    rp.setLimitNum(limitNum);
                    try{
                        resetPasswordService.save(rp);
                    }catch (Exception e) {
                        System.out.println(e);
                    }
                }
                // 发送html邮件到对应的邮箱号
                String title = "重置密码-来自山东大学教学信息管理系统后台";
                String content = "<html>\n" +
                        "<body>\n" +
                        "<h3>" + "用户" + username + ": 你好"+"</h3>\n" +
                        "<p>" + "你正在山东大学教学信息管理系统进行重置密码操作<br/>" +
                        "您本次重置密码的验证码为<br/>" +
                        "<p style=\"font-size:24px; color: #409EFF\">" + code + "</p>" +
                        "<br/>请在5分钟之内填写验证码"+
                        "<br/>如果非本人操作，请忽略本邮件, 如有疑问，欢迎致信752869332@qq.com" +
                        "</p>" +
                        "</body>\n" +
                        "</html>\n";
                // 发送Html邮件时间相对较长
                emailService.sendHtmlMail(email, title, content);
                return ResultFactory.buildSuccessResult(username);
            }
            return ResultFactory.buildFailResult("用户邮箱号输入错误，请重新输入");
        }
        return ResultFactory.buildFailResult("该用户未注册，请先注册账号");
    }

    /**
     * 检查验证码是否正确
     * @param code 验证码
     * @param username 用户username
     * @return Result信息
     */
    @Override
    public Result checkCode(String code, String username) {
        Long userId = findIdByUsername(username);
        ResetPassword rp = resetPasswordService.getOneByUserId(userId);
        // 获取当前时间
        Date currentTime = new Date();
        if (currentTime.after(rp.getDeadline())) {
            return ResultFactory.buildFailResult("验证时间已过,请刷新界面,从头开始重置密码！");
        }else {
            if (rp.getCode().equals(code)) {
                return ResultFactory.buildSuccessResult("验证码匹配正确!", username);
            }
            return ResultFactory.buildFailResult("验证码匹配错误!");
        }
    }

    /**
     * 重置密码
     * @param password 输入密码的md5密文
     * @param username 用户名
     * @return Result信息
     */
    @Override
    public Result resetPassword(String username, String password) {
        Long userId = findIdByUsername(username);
        SysUser sysUser = sysUserService.getById(userId);
//        System.out.println(password);
        sysUser.setPassword(password);
        //更新到数据库
        sysUserService.updateById(sysUser);
        return ResultFactory.buildSuccessResult("修改密码成功!");
    }

    /**
     * 判断数据库中是否存在用户
     * @param username 用户名
     * @return true、false
     */
    @Override
    public boolean isExistUser(String username) {
        //mybatis-plus的条件构造器queryWrapper
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return sysUserService.count(queryWrapper) > 0;
    }

    /**
     * 通过用户名查找对应的邮箱号
     * @param username 用户名
     * @return email 邮箱号
     */
    @Override
    public String findEmailByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        //找到数据库中用户名和输入用户名相同的一条数据
        SysUser user = sysUserService.getOne(queryWrapper);
        //返回对应的邮箱
        return user.getEmail();
    }

    /**
     * 通过用户名查找对应的用户id
     * @param username 用户名
     * @return id
     */
    @Override
    public Long findIdByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        //找到数据库中用户名和输入用户名相同的一条数据
        SysUser user = sysUserService.getOne(queryWrapper);
        //返回对应的用户id
        return user.getUserId();
    }
}
