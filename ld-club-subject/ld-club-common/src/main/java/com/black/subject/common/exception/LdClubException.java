package com.black.subject.common.exception;

/**
 * @author 韩志远
 * @version 1.0
 * @description 本项目自定义异常类型
 */
public class LdClubException extends RuntimeException {

    private String errMessage;

    public LdClubException() {
    }

    public LdClubException(String message) {
        super(message);
        this.errMessage = message;

    }

    public String getErrMessage() {
        return errMessage;
    }



    public static void cast(String message){
        throw new LdClubException(message);
    }
    public static void cast(CommonError error){
        throw new LdClubException(error.getErrMessage());
    }

}

