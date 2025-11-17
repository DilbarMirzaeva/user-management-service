package com.webapp.usermanagementservice.repository;

import com.webapp.usermanagementservice.model.entity.User;
import com.webapp.usermanagementservice.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    List<User> findByStatus(Status status);
}
