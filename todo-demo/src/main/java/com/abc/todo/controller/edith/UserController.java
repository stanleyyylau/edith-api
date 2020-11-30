package com.abc.todo.controller.edith;

import com.abc.todo.dto.LoginDTO;
import com.abc.todo.common.exception.http.NotFoundException;
import com.abc.todo.common.exception.http.ParameterException;
import com.abc.todo.model.User;
import com.abc.todo.service.UserIdentityService;
import com.abc.todo.service.UserService;
import com.anton.edith.core.token.DoubleJWT;
import com.anton.edith.core.token.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserIdentityService userIdentityService;

    @Autowired
    private DoubleJWT jwt;

    @PostMapping("/login")
    public Tokens login(@RequestBody LoginDTO validator) {
        User user = userService.getUserByUsername(validator.getUsername());
        if (user == null) {
            throw new NotFoundException(10021);
        }
        boolean valid = userIdentityService.verifyUsernamePassword(
                user.getId(),
                user.getUsername(),
                validator.getPassword());
        if (!valid) {
            throw new ParameterException(10031);
        }
        return jwt.generateTokens(user.getId());
    }

}
