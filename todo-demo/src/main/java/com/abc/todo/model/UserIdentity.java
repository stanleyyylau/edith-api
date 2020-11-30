package com.abc.todo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "edith_user_identity")
@Getter
@Setter
@Where(clause = "delete_time is null ")
public class UserIdentity extends BaseEntity {
    @Id
    private Integer id;
    private Integer userId;
    private String identityType;
    private String identifier;
    private String credential;
}
