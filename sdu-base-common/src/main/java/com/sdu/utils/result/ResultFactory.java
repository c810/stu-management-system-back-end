package com.sdu.utils.result;

/**
 * @author Connor
 * @date 2022/11/12 1:41
 * @description
 */
public class ResultFactory {
    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }

    /**
     * 连接错误
     * @param message 错误信息
     * @return Result
     */
    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    /**
     * 连接成功
     * @param data 返回数据
     * @return Result
     */
    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "成功", data);
    }

    /**
     * 操作成功
     * @param message 提示信息
     * @param data 传回数据
     * @return Result对象
     */
    public static Result buildSuccessResult(String message, Object data) {
        return buildResult(ResultCode.SUCCESS, message, data);
    }
}
