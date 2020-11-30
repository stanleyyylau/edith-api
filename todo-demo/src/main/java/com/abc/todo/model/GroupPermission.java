package com.abc.todo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "edith_group_permission")
@Getter
@Setter
public class GroupPermission {
    @Id
    private Long id;
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "permission_id")
    private Long permissionId;
}
