package com.abc.todo.repository;

import com.abc.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String userName);

    User getById(Integer id);
}
