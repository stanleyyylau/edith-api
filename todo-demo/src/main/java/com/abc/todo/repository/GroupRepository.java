package com.abc.todo.repository;


import com.abc.todo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> getAllByIdIn(List<Integer> ids);
}
