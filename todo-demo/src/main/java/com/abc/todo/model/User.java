package com.abc.todo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "edith_user")
@Getter
@Setter
@Where(clause = "delete_time is null ")
public class User extends BaseEntity {

    @Id
    private Integer id;
    private String username;
    private String nickname;
    private String avatar;
    private String email;
}
