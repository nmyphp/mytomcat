package com.github.nmyphp.mytomcat;

public enum StatusCode {
    OK(200),
    NOT_FOUND(404),
    SERVER_ERROR(500);

    private int code;
    StatusCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "HTTP/1.1 " + code;
    }
}
