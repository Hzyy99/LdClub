package com.black.gateway.entity;

import com.black.gateway.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result<T> {

    private Integer code;

    private String msg;

    private Boolean success;

    private T data;

    public static Result success() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMsg(ResultCodeEnum.FAIL.getMessage());
        return result;
    }

    public static <T> Result success(T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result fail(T data) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMsg(ResultCodeEnum.FAIL.getMessage());
        result.setData(data);
        return result;
    }

    public static Result fail(Integer code,String message) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

}
