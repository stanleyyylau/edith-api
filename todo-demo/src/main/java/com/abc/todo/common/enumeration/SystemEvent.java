package com.abc.todo.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum SystemEvent {
    TODO_NOT_FOUND(10130, "Todo Not Found");

    private int code;

    private String description;

    SystemEvent(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
