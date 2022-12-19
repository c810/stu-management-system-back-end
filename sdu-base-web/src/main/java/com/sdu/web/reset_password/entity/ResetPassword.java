package com.sdu.web.reset_password.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Connor
 * @date 2022/11/12 1:18
 * @description 重置密码实体，对应resetPassword表
 */
@Data
@TableName("reset_password")
@Repository
public class ResetPassword {
    // 主键id
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    // 随机生成的16位验证码
    private String code;
    private Date createTime;
    private Date deadline;
    // 是否有效，若当前时间超出截至时间，则判定当前验证码无效，0代表无效，1代表有效
    private int isEffective;
    // 重置次数，记录当前重置次数
    private int resetNum;
    // 当日限定重置次数, 默认为3次
    private int limitNum;
}
