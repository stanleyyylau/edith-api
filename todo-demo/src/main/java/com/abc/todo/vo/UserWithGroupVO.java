package com.abc.todo.vo;

import com.abc.todo.model.Group;
import com.abc.todo.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
public class UserWithGroupVO extends User {

    public List<Group> group;

    public UserWithGroupVO(User user, List<Group> group) {
        BeanUtils.copyProperties(user, this);
        this.group = group;
    }
}
