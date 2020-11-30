package com.abc.todo.common.exception;

import com.abc.todo.common.exception.http.HttpException;

public class TokenInvalidException extends HttpException {
    public TokenInvalidException(int code) {
        this.httpStatusCode = 200;
        this.code = code;
    }
}
