package com.abc.todo.common.exception.http;

import com.abc.todo.common.enumeration.SystemEvent;

public class ParameterException extends HttpException {
    public ParameterException(int code) {
        this.code = code;
        this.httpStatusCode = 400;
    }

    public ParameterException(SystemEvent event) {
        this.code = event.getCode();
        this.httpStatusCode = 200;
    }
}
