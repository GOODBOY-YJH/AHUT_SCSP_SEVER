package com.example.ahut_scsp.util;

public class ScspException extends RuntimeException{
    private String msg;
    private int code = 500;

    public ScspException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ScspException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ScspException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ScspException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
