package com.model.exception;

/**
 * 自定义运行时异常
 */
public class CommonException extends RuntimeException {
    public CommonException(String msg) {
        super(msg);
    }
}