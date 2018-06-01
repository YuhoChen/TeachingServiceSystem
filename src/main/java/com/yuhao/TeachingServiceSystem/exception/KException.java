package com.yuhao.TeachingServiceSystem.exception;

public class KException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KException() {
    }

    public KException(String message) {
        super(message);
    }

    public KException(String message, Throwable t) {
        super(message, t);
    }

    public KException(Throwable t) {
        super(t);
    }

    public KException(Throwable t, Throwable t2) {
        super(t.getMessage(), t2);
    }
}
