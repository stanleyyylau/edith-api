package com.abc.todo.common.exception;

import com.abc.todo.common.exception.http.HttpException;

public class TokenExpiredException extends HttpException {
    public TokenExpiredException(int code) {
        this.httpStatusCode = 200;
        this.code = code;
    }
}
