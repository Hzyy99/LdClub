package com.black.auth.common.exception;

import com.black.auth.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Black
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandlerAuth {

    //对项目的自定义异常类型进行处理
    @ResponseBody
    @ExceptionHandler(LdClubException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result customException(LdClubException e) {
        //记录异常
        log.error("系统异常{}", e.getErrMessage(), e);
        //..
        //解析出异常信息
        String errMessage = e.getErrMessage();
        return Result.fail(errMessage);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception e) {

        //记录异常
        log.error("系统异常{}", e.getMessage(), e);
        if (e.getMessage().equals("不允许访问")) {
            Result.fail("您没有操作权限");
        }
        //解析出异常信息
        return Result.fail(e.getMessage());
    }
    //MethodArgumentNotValidException
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        //存储错误信息
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(item -> {
            errors.add(item.getDefaultMessage());
        });

        //将list中的错误信息拼接起来
        String errMessage = StringUtils.join(errors, ",");
        //记录异常
        log.error("系统异常{}", e.getMessage(), errMessage);

        //解析出异常信息
        return Result.fail(errMessage);
    }


}
