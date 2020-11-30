package com.abc.todo.repository;

import com.abc.todo.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> getAllByMount(Boolean mount);

    Permission getByNameAndModule(String name, String module);
}
