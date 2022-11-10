package com.sdu.web.school_teacher.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Connor
 * @date 2022/11/9 14:02
 * 导入导出实体类
 */
@Data
public class ExportStuExcel {
    @Excel(name = "学号", orderNum = "1", width=30)
    private String stuNum;
    @Excel(name = "姓名", orderNum = "2", width=30)
    private String stuName;
    @Excel(name = "成绩", orderNum = "3", width=15)
    private BigDecimal points;
}
