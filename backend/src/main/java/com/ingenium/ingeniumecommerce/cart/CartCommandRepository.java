package com.ingenium.ingeniumecommerce.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartCommandRepository extends JpaRepository<Cart, Long> {
}