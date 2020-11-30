package com.abc.todo.service;

import org.springframework.stereotype.Service;

@Service
public class GroupService {

    public boolean checkIsRootByUserId(Integer userId) {
        return userId == 1;
    }

}
