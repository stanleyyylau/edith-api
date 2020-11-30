package com.abc.todo.service;

import com.abc.todo.model.Group;
import com.abc.todo.model.Permission;
import com.abc.todo.model.User;
import com.abc.todo.model.UserGroup;
import com.abc.todo.repository.GroupRepository;
import com.abc.todo.repository.UserGroupRepository;
import com.abc.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Permission> getUserPermissions(Integer userId) {
        List<Permission> permissions = new ArrayList<>();
        List<Group> userGroups = this.getUserGroupByUserId(userId);
        for (Group userGroup : userGroups) {
            permissions.addAll(userGroup.getPermissionList());
        }
        return permissions;
    }

    public User getById(Integer userId) {
        return userRepository.getById(userId);
    }

    public List<Group> getUserGroupByUserId(Integer userId) {
        List<UserGroup> userGroups = userGroupRepository.getAllByUserId(userId);
        List<Integer> groupIds = userGroups.stream().map(UserGroup::getGroupId).collect(Collectors.toList());
        return groupRepository.getAllByIdIn(groupIds);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String userName) {
        return userRepository.getByUsername(userName);
    }
}
