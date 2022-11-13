package com.ingenium.ingeniumecommerce.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartQueryRepository extends JpaRepository<Cart, Long> {
    Optional<CartView> findCartById(final Long cartID);
    List<CartView> findAllBy();
}