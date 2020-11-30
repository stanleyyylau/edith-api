package com.abc.todo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "edith_file")
@Getter
@Setter
@Where(clause = "delete_time is null ")
public class File extends BaseEntity {
    @Id
    private Long id;
    private String path;
    private String type;
    private String name;
    private String extension;
    private Integer size;
    private String md5;
}
