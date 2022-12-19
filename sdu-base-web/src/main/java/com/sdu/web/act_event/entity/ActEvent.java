package com.sdu.web.act_event.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("act_event")
public class ActEvent {
    @TableId(type = IdType.AUTO)
    private Long eventId;
    @TableField(exist = false)
    private Long stuId;
    @TableField(exist = false)
    private String stuName;
    private String stuNum;
    private String eventType;
    private String eventName;
    private String eventStart;
    private String eventEnd;
    private String eventResult;
}
