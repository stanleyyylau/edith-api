package com.abc.todo.common.exception.http;

public class HttpException extends RuntimeException {
    protected Integer code;

    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    protected Integer httpStatusCode = 500;

    public boolean isMessageOnly() {
        return false;
    }

    public Integer getHttpCode() {
        return httpStatusCode;
    }
}
