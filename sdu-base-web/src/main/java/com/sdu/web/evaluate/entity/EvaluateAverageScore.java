package com.sdu.web.evaluate.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/17 19:43
 * @description
 */
@Data
public class EvaluateAverageScore {
    private Double teachLevelAverageScore;
    private Double homeworkAverageScore;
    private Double attitudeAverageScore;
    private Double bookChosenAverageScore;
    private Double reactionAverageScore;
}
