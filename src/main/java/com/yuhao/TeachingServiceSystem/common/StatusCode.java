package com.yuhao.TeachingServiceSystem.common;

public enum StatusCode {
    OK(200),
    ERROR(300);
    private int code;
    private StatusCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

}
