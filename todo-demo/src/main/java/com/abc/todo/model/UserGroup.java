package com.abc.todo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "edith_user_group")
@Getter
@Setter
public class UserGroup {
    @Id
    private Integer id;
    private Integer userId;
    private Integer groupId;
}
