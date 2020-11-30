package com.abc.todo.service;

import com.abc.todo.model.UserIdentity;
import com.abc.todo.repository.UserIdentityRepository;
import com.anton.edith.core.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIdentityService {

    @Autowired
    private UserIdentityRepository userIdentityRepository;

    public boolean verifyUsernamePassword(Integer userId, String username, String password) {
        UserIdentity user = userIdentityRepository.getByUserIdAndIdentifierAndIdentityType(userId, username, "USERNAME_PASSWORD");
        return EncryptUtil.verify(user.getCredential(), password);
    }

}
