package com.ingenium.ingeniumecommerce.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQueryRepository extends JpaRepository<User, Long> {
}