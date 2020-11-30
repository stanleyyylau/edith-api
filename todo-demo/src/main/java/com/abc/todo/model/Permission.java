package com.abc.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "edith_permission")
@Getter
@Setter
@Where(clause = "delete_time is null ")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity {
    @Id
    private long id;
    private String name;
    private String module;

    @JsonIgnore
    private Boolean mount;


}
