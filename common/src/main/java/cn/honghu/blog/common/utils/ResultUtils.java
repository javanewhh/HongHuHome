package cn.honghu.blog.common.utils;

import cn.honghu.blog.common.enums.ReturnCodeEnum;
import cn.honghu.blog.common.web.response.Result;

public class ResultUtils {

    public static Result build(boolean success) {
        if (success) {
            return ResultUtils.buildSuccess();
        } else {
            return ResultUtils.buildFailed();
        }
    }

    public static Result buildSuccess() {
        return new Result(ReturnCodeEnum.SUCCESS);
    }

    public static Result buildSuccess(Object object) {
        return new Result(ReturnCodeEnum.SUCCESS, object);
    }

    public static Result buildException(ReturnCodeEnum returnCommonTypeEnum) {
        return new Result(returnCommonTypeEnum.getCode(), returnCommonTypeEnum.getMsg());
    }

    public static Result buildException() {
        return new Result(ReturnCodeEnum.INTERNAL_SERVICE_EXCEPTION.getCode(),
                ReturnCodeEnum.INTERNAL_SERVICE_EXCEPTION.getMsg());
    }

    public static Result buildException(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result buildError(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result buildError(ReturnCodeEnum returnCommonTypeEnum, String msg) {
        return new Result(returnCommonTypeEnum.getCode(), msg);
    }

    public static Result buildError(ReturnCodeEnum returnCommonTypeEnum, String msg, Object data) {
        return new Result(returnCommonTypeEnum.getCode(), msg, data);
    }

    public static Result buildFailed() {
        return new Result(ReturnCodeEnum.FAILED.getCode(), ReturnCodeEnum.FAILED.getMsg());
    }

    public static Result buildFailed(String msg) {
        return new Result(ReturnCodeEnum.FAILED.getCode(), msg);
    }

    public static Result paramError(String msg) {
        return new Result(ReturnCodeEnum.PARAM_ERROR.getCode(), msg);
    }
}
