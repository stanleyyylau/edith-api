package com.abc.todo.common.listener;

import com.anton.edith.autoconfigure.bean.MetaInfo;
import com.anton.edith.autoconfigure.bean.PermissionMetaCollector;
import com.abc.todo.model.Permission;
import com.abc.todo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PermissionHandleListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMetaCollector metaCollector;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addNewPermissions();
        removeUnusedPermissions();
    }

    private void addNewPermissions() {
        metaCollector.getMetaMap().values().forEach(meta -> {
            String module = meta.getModule();
            String permission = meta.getPermission();
            createPermissionIfNotExist(permission, module);
        });
    }

    private void removeUnusedPermissions() {
        List<Permission> allPermissions = permissionService.list();
        Map<String, MetaInfo> metaMap = metaCollector.getMetaMap();
        for (Permission permission : allPermissions) {
            boolean stayedInMeta = metaMap
                    .values()
                    .stream()
                    .anyMatch(meta -> meta.getModule().equals(permission.getModule())
                            && meta.getPermission().equals(permission.getName()));
            if (!stayedInMeta) {
                permission.setMount(false);
                permissionService.updateById(permission);
            }
        }
    }

    private void createPermissionIfNotExist(String name, String module) {
        Permission permission = permissionService.getByNameAndModule(name, module);
        if (permission == null) {
            permissionService.save(Permission.builder().module(module).name(name).mount(true).build());
        }
        if (permission != null && !permission.getMount()) {
            permission.setMount(true);
            permissionService.updateById(permission);
        }
    }
}
