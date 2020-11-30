package com.abc.todo.common.exception;

import com.abc.todo.common.exception.http.HttpException;

public class AuthenticationException extends HttpException {
    public AuthenticationException(int code) {
        this.httpStatusCode = 201;
        this.code = code;
    }
}
