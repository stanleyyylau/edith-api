package com.abc.todo.service;

import com.abc.todo.model.Permission;
import com.abc.todo.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public List<Permission> getAllPermissions() {
        return permissionRepository.getAllByMount(true);
    }

    public Map<String, List<Permission>> getStructuringPermissions(List<Permission> permissions) {
        Map<String, List<Permission>> resultMap = new HashMap<>();
        for (Permission permission : permissions) {

            List<Permission> currentModule = resultMap.get(permission.getModule());
            if (currentModule == null) {
                currentModule = new ArrayList<>();
                resultMap.put(permission.getModule(), currentModule);
            }

            currentModule.add(permission);
        }
        return resultMap;
    }

    public List<Permission> list() {
        return permissionRepository.findAll();
    }

    public void updateById(Permission permission) {
        permissionRepository.save(permission);
    }

    public Permission getByNameAndModule(String name, String module) {
        return permissionRepository.getByNameAndModule(name, module);
    }

    public void save(Permission permission) {
        permissionRepository.save(permission);
    }

}
