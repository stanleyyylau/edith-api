package com.abc.todo.controller.edith;

import com.abc.todo.model.Group;
import com.abc.todo.model.Permission;
import com.abc.todo.service.PermissionService;
import com.abc.todo.service.UserService;
import com.abc.todo.vo.UserWithGroupVO;
import com.anton.edith.core.annotation.AdminRequired;
import com.anton.edith.core.annotation.GroupRequired;
import com.anton.edith.core.annotation.PermissionMeta;
import com.anton.edith.core.annotation.PermissionModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cms/admin")
@PermissionModule(value = "管理员")
public class AdminController {

    @Autowired
    PermissionService permissionService;

    @Autowired
    UserService userService;

    @GetMapping("/permission")
    @GroupRequired
    @PermissionMeta(value = "查询所有可分配的权限")
    public Map<String, List<Permission>> getAllPermissions() {
        List<Permission> allPermissions = permissionService.getAllPermissions();
        return permissionService.getStructuringPermissions(allPermissions);
    }

    @GetMapping("/users")
    @AdminRequired
    @PermissionMeta(value = "查询所有用户")
    public List<UserWithGroupVO> getUsers() {
        return userService.getAllUsers().stream()
                .map(user -> {
                    List<Group> groups = userService.getUserGroupByUserId(user.getId());
                    return new UserWithGroupVO(user, groups);
                })
                .collect(Collectors.toList());
    }

}
