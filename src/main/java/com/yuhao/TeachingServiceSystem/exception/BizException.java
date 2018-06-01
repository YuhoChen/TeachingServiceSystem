package com.yuhao.TeachingServiceSystem.exception;

public class BizException extends KException {
    private static final long serialVersionUID = 1L;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }
}
