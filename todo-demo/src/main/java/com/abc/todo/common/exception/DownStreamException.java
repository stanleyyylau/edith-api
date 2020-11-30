package com.abc.todo.common.exception;

import com.abc.todo.common.enumeration.SystemEvent;
import com.abc.todo.common.exception.http.HttpException;

public class DownStreamException extends HttpException {
    public DownStreamException(SystemEvent event) {
        this.httpStatusCode = 200;
        this.code = event.getCode();
    }
}
