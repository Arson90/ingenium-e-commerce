package com.ingenium.ingeniumecommerce.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCommandRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(final String username);
}