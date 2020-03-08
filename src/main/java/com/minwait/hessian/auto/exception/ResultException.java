package com.minwait.hessian.auto.exception;


/**
 * 自定义code的ResultException
 *
 * @author xunmi
 */
public class ResultException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public ResultException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
