package com.sdu.web.act_event.entity;

import lombok.Data;

@Data
public class EventListPara {
    private Long currentPage;
    private Long pageSize;
    private Long eventId;
    private String eventName;
    private String eventType;
    private String eventStart;
    private String eventEnd;
    private Long stuId;
    private String stuNum;
}
