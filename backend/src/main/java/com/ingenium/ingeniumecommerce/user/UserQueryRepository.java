package com.ingenium.ingeniumecommerce.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<User, Long> {
    Optional<UserView> findUserById(final Long userId);
    List<UserView> findAllBy();
}