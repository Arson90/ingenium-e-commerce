package com.ingenium.ingeniumecommerce.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserQueryRepository extends JpaRepository<User, Long> {
    Optional<UserView> findUserById(final Long userId);
    List<UserView> findAllBy();
}