package com.abc.todo.service;

import com.abc.todo.model.Log;
import com.abc.todo.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void createLog(String message, String permission, Integer userId,
                          String username, String method, String path,
                          Integer status) {
        Log record = Log.builder()
                .message(message)
                .userId(userId)
                .username(username)
                .statusCode(status)
                .method(method)
                .path(path)
                .build();
        if (permission != null) {
            record.setPermission(permission);
        }
        logRepository.save(record);
    }

}
