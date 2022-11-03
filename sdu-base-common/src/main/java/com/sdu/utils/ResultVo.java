package com.sdu.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Connor
 * @date 2022/11/2 22:30
 * 封装返回值
 */
@Data  // lombok自动生成get和set方法
@AllArgsConstructor  // lombok自动生成所有构造函数
public class ResultVo<T> {
    private String msg;
    private int code;
    private T data;
}
