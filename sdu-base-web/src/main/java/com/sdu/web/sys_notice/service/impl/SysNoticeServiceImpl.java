package com.sdu.web.sys_notice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdu.web.sys_notice.entity.SysNotice;
import com.sdu.web.sys_notice.mapper.SysNoticeMapper;
import com.sdu.web.sys_notice.service.SysNoticeService;
import org.springframework.stereotype.Service;

/**
 * @author Connor
 * @date 2022/11/10 0:10
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {
}
