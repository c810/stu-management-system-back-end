package com.sdu.web.school_student.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/10 13:31
 */
@Data
public class StuExcel2 {
    @Excel(name = "姓名（必填）", orderNum = "1", width=20)
    private String stuName;
    @Excel(name = "性别（必填）", replace = {"男_0","女_1"}, orderNum = "2", width=20)
    private String sex;
    @Excel(name = "电话（必填）", orderNum = "3", width=20)
    private String phone;
    @Excel(name = "邮箱", orderNum = "4", width=20)
    private String email;
    @Excel(name = "政治面貌（党员、团员、群众）", replace = {"党员_0","团员_1","群众_2"}, orderNum = "5", width=40)
    private String politicalFace;
    @Excel(name = "籍贯", orderNum = "6", width=20)
    private String nativePlace;
    @Excel(name = "民族", orderNum = "7", width=10)
    private String nation;
    @Excel(name = "身份证", orderNum = "8", width=20)
    private String idCard;
    @Excel(name = "居住地", orderNum = "9", width=30)
    private String location;
}
