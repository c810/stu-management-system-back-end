package com.sdu.web.evaluate.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdu.utils.ResultUtils;
import com.sdu.utils.ResultVo;
import com.sdu.web.evaluate.entity.Evaluate;
import com.sdu.web.evaluate.entity.EvaluateAverageScore;
import com.sdu.web.evaluate.entity.EvaluatePara;
import com.sdu.web.evaluate.entity.EvaluateVo;
import com.sdu.web.evaluate.service.EvaluateService;
import com.sdu.web.school_college.entity.SchoolCollege;
import com.sdu.web.school_student.entity.SchoolStudent;
import com.sdu.web.school_student.entity.StuPara;
import com.sdu.web.school_teacher.entity.SchoolTeacher;
import com.sdu.web.school_teacher.service.SchoolTeacherService;
import com.sdu.web.sys_menu.entity.SysMenu;
import com.sdu.web.sys_role.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Connor
 * @date 2022/11/13 22:53
 * @description
 */
@RestController
@RequestMapping("/api/evaluate")
public class EvaluateController {
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private SchoolTeacherService schoolTeacherService;

    // 查询学生教师列表
    @GetMapping("/getTeacherList")
    public ResultVo getTeacherList(EvaluatePara para) {
        IPage<EvaluateVo> list = evaluateService.getTeacherList(para);
        list.getRecords().stream().forEach(item -> {
            QueryWrapper<Evaluate> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Evaluate::getTeacherId,item.getTeacherId());
            List<Evaluate> evaluateList = evaluateService.list(queryWrapper);
            for(int i = 0; i < evaluateList.size(); i ++){
                if(evaluateList.get(i).getStuId() == para.getStuId()){
                    item.setStuId(para.getStuId());
                }
            }
        });
        return ResultUtils.success("查询成功!", list);
    }

    @PostMapping
    public ResultVo add(@RequestBody Evaluate evaluate) {
        evaluate.setEvaluateDate(new Date());
        evaluateService.save(evaluate);
        return ResultUtils.success("新增成功!");
    }

    // 根据学生id查询评价
    @GetMapping("/getEvaluateByStuId")
    public ResultVo getEvaluateByStuId(Long stuId) {
        QueryWrapper<Evaluate> query = new QueryWrapper<>();
        query.lambda().eq(Evaluate::getStuId, stuId);
        List<Evaluate> list = evaluateService.list(query);
        return ResultUtils.success("查询成功!", list);
    }

    // 根据教师id查询评价
    @GetMapping("/getMyEvaluateList")
    public ResultVo getMyEvaluateList(EvaluatePara evaluatePara) {
        IPage<Evaluate> list = evaluateService.getMyEvaluateList(evaluatePara);
        return ResultUtils.success("查询成功!", list);
    }

    // 根据教师id查询评价平均分
    @GetMapping("/getEvaluateAverageScoreList")
    public ResultVo getEvaluateAverageScoreList(Long teacherId) {
        QueryWrapper<Evaluate> query = new QueryWrapper<>();
        query.lambda().eq(Evaluate::getTeacherId,teacherId);
        List<Evaluate> list = evaluateService.list(query);
        EvaluateAverageScore evaluateAverageScore = new EvaluateAverageScore();
        if(list.size()>0){
            Double teachLevelScore = (double) 0,homeworkScore = (double) 0,attitudeScore = (double) 0,bookChosenScore = (double) 0,reactionScore = (double) 0;
            for (Evaluate evaluate : list) {
                teachLevelScore += evaluate.getTeachLevel();
                reactionScore += evaluate.getReaction();
                attitudeScore += evaluate.getAttitude();
                homeworkScore += evaluate.getHomework();
                bookChosenScore += evaluate.getBookChosen();
            }
            evaluateAverageScore.setTeachLevelAverageScore(teachLevelScore/list.size());
            evaluateAverageScore.setReactionAverageScore(reactionScore/list.size());
            evaluateAverageScore.setAttitudeAverageScore(attitudeScore/list.size());
            evaluateAverageScore.setHomeworkAverageScore(homeworkScore/list.size());
            evaluateAverageScore.setBookChosenAverageScore(bookChosenScore/list.size());
        }else {
            evaluateAverageScore.setTeachLevelAverageScore((double) 0);
            evaluateAverageScore.setReactionAverageScore((double) 0);
            evaluateAverageScore.setAttitudeAverageScore((double) 0);
            evaluateAverageScore.setHomeworkAverageScore((double) 0);
            evaluateAverageScore.setBookChosenAverageScore((double) 0);
        }
        return ResultUtils.success("查询成功!", evaluateAverageScore);
    }

    @PutMapping("/isEvaluated")
    public ResultVo isEvaluated(@RequestBody SchoolTeacher schoolTeacher) {
        if (schoolTeacher.getUnreadMessage() == null) {
            schoolTeacher.setUnreadMessage(1);
        }else if(schoolTeacher.getUnreadMessage() >= 0){
            schoolTeacher.setUnreadMessage(schoolTeacher.getUnreadMessage()+1);
        }
//        System.out.println(schoolTeacher.getUnreadMessage());
        boolean save = schoolTeacherService.updateById(schoolTeacher);
        if (save) {
            return ResultUtils.success("增加未读消息成功!");
        }
        return ResultUtils.error("增加未读消息失败!");
    }

    @PutMapping("/clearUnreadEvaluate")
    public ResultVo clearUnreadEvaluate(@RequestBody SchoolTeacher schoolTeacher) {
        if (schoolTeacher.getUnreadMessage() == null) {
            schoolTeacher.setUnreadMessage(0);
        }else if(schoolTeacher.getUnreadMessage() >= 0){
            schoolTeacher.setUnreadMessage(0);
        }
        boolean save = schoolTeacherService.updateById(schoolTeacher);
        if (save) {
            return ResultUtils.success("全部消息已读!");
        }
        return ResultUtils.error("未读消息置0失败!");
    }


}
