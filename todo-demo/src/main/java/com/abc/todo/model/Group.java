package com.abc.todo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "edith_group")
@Getter
@Setter
@Where(clause = "delete_time is null ")
public class Group extends BaseEntity {
    @Id
    private Integer id;
    private String name;
    private String info;

    /**
     * 分组级别（root、guest、user，其中 root、guest 分组只能存在一个）
     */
    private String level;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lin_group_permission",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissionList;
}
