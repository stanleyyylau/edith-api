package com.abc.todo.common.exception;

import com.abc.todo.common.exception.http.HttpException;

public class UpdateSuccess extends HttpException {
    public UpdateSuccess(int code) {
        this.httpStatusCode = 200;
        this.code = code;
    }
//    201 202 204
}
