package com.abc.todo.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "edith_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "delete_time is null ")
public class Log extends BaseEntity {
    @Id
    private Integer id;
    private String message;
    private Integer userId;
    private String username;
    private Integer statusCode;
    private String method;
    private String path;
    private String permission;

}
