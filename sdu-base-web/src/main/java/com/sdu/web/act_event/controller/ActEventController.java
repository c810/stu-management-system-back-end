package com.sdu.web.act_event.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.act_event.entity.ActEvent;
import com.sdu.web.act_event.entity.EventListPara;
import com.sdu.web.act_event.service.ActEventService;
import com.sdu.web.school_student.entity.SchoolStudent;
import com.sdu.web.school_student.service.SchoolStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class ActEventController {
    @Autowired
    private ActEventService actEventService;
    @Autowired
    private SchoolStudentService schoolStudentService;

    @PostMapping
    public ResultVo add(@RequestBody ActEvent actEvent){
        /*System.out.println("==============================================");
        System.out.println(actPractice.getStuNum());
        System.out.println(actPractice.getStuId());*/
        QueryWrapper<SchoolStudent> query = new QueryWrapper<>();
        if(actEvent.getStuId() != null ){
            query.lambda().eq(SchoolStudent::getStuId,actEvent.getStuId());

        }else{
            query.lambda().eq(SchoolStudent::getStuNum,actEvent.getStuNum());
        }
        SchoolStudent student = schoolStudentService.getOne(query);
        if(student == null){
            return ResultUtils.error("学生不存在,请检查学号!");
        }

        boolean save = actEventService.save(actEvent);
        if(save){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

// 编辑
    @PutMapping
    public ResultVo edit(@RequestBody ActEvent actEvent){
        QueryWrapper<SchoolStudent> query = new QueryWrapper<>();
        query.lambda().eq(SchoolStudent::getStuNum,actEvent.getStuNum());
        SchoolStudent student = schoolStudentService.getOne(query);
        if(student == null){
            return ResultUtils.error("学生不存在!");
        }
        boolean save = actEventService.updateById(actEvent);
        if(save){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    // 删除
    @DeleteMapping("/{eventId}")
    public ResultVo delete(@PathVariable("eventId") Long eventId){
        boolean save = actEventService.removeById(eventId);
        if(save){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    // 列表
    @GetMapping("/list")
    public ResultVo list(EventListPara para){
        IPage<ActEvent> list = actEventService.getEventList(para);
        return ResultUtils.success("查询成功!",list);
    }
}
