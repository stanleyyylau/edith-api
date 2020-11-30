package com.abc.todo.common.exception;

import com.abc.todo.common.exception.http.HttpException;

public class CreateSuccess extends HttpException {
    public CreateSuccess(int code) {
        this.httpStatusCode = 201;
        this.code = code;
    }
//    201 202 204
}
