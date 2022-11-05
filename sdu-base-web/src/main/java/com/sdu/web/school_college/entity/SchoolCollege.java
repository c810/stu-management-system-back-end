package com.sdu.web.school_college.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Connor
 * @date 2022/11/5 15:41
 */
@Data
@TableName("school_college")
public class SchoolCollege {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long collegeId;
    // 学院名称
    private String collegeName;
    // 序号
    private Integer orderNum;
    // 创建时间
    private Date createTime;
}
