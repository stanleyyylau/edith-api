package com.abc.todo.repository;

import com.abc.todo.model.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIdentityRepository extends JpaRepository<UserIdentity, Integer> {

    UserIdentity getByUserIdAndIdentifierAndIdentityType(Integer user, String identifier, String type);

}
