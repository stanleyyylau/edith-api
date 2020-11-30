package com.abc.todo.common.exception;

import com.abc.todo.common.exception.http.HttpException;

public class AuthorizationException extends HttpException {
    public AuthorizationException(int code) {
        this.httpStatusCode = 201;
        this.code = code;
    }
}
