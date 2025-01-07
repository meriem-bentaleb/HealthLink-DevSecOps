package com.Health.Link.Auth.repository;

import com.Health.Link.Auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
